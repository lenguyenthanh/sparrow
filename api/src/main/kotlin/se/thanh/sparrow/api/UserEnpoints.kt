package se.thanh.sparrow.api

import arrow.core.left
import arrow.core.right
import arrow.endpoint.Codec
import arrow.endpoint.Endpoint
import arrow.endpoint.ArrowEndpoint
import arrow.endpoint.server.ServerEndpoint
import arrow.endpoint.DecodeResult
import arrow.endpoint.EndpointInput
import arrow.endpoint.EndpointOutput
import arrow.endpoint.JsonCodec
import arrow.endpoint.Schema
import arrow.endpoint.and
import arrow.endpoint.errorOutput
import arrow.endpoint.input
import arrow.endpoint.model.StatusCode
import arrow.endpoint.output
import arrow.endpoint.product
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public val usersBase: EndpointInput<Unit> =
  ArrowEndpoint.fixedPath("api").and(ArrowEndpoint.fixedPath("users"))

@Serializable
public data class User(
  val id: Long,
  val name: String,
  val login: String,
  val email: String,
  val avatar: String? = null
) {
  public companion object {
    public val schema: Schema<User> = Schema.product(
      User::id to Schema.long,
      User::name to Schema.string,
      User::login to Schema.string,
      User::email to Schema.string,
      User::avatar to Schema.string.asNullable(),
    )

    public val jsonCodec: JsonCodec<User> = Codec.kotlinxJson(schema)
    public val jsonCodecAsList: JsonCodec<List<User>> = Codec.kotlinxJson(schema.asList())
  }
}

@Serializable
public data class CreateUser(
  val id: Long,
) {
  public companion object {
    public val schema: Schema<CreateUser> = Schema.product(
      CreateUser::id to Schema.long,
    )

    public val jsonCodec: JsonCodec<CreateUser> = Codec.kotlinxJson(schema)
  }
}

@OptIn(ExperimentalSerializationApi::class)
public inline fun <reified A> Codec.Companion.kotlinxJson(schema: Schema<A>): JsonCodec<A> =
  json(schema, { DecodeResult.Value(Json.decodeFromString(it)) }) { Json.encodeToString(it) }

public val errorOutput: List<EndpointOutput.StatusMapping<ErrorResponse>> =
  ErrorCode.values() // .map { it.toStatusCode() }
    .map {
      ArrowEndpoint.statusMapping(
        it.statusCode(),
        ArrowEndpoint.anyJsonBody(ErrorResponse.jsonCodec)
      ) { any -> (any as? ErrorResponse)?.error?.code == it }
    }

public val findAll: Endpoint<Unit, ErrorResponse, List<User>> =
  Endpoint.get()
    .input(usersBase)
    .output(
      ArrowEndpoint.anyJsonBody(User.jsonCodecAsList)
        .description("Find all users")
        .default(emptyList())
        .example(emptyList())
    )
    .output(ArrowEndpoint.statusCode(StatusCode.Created))
    .errorOutput(EndpointOutput.OneOf(errorOutput, Codec.idPlain()))

public val findById: Endpoint<Int, ErrorResponse, User> =
  Endpoint.get()
    .input(usersBase)
    .input(ArrowEndpoint.path("id", Codec.int))
    .output(
      ArrowEndpoint.anyJsonBody(User.jsonCodec)
        .description("Find all users")
    )
    .output(ArrowEndpoint.statusCode(StatusCode.Ok))
    .errorOutput(EndpointOutput.OneOf(errorOutput, Codec.idPlain()))

public val createUser: Endpoint<Pair<String, CreateUser>, ErrorResponse, User> =
  Endpoint.put()
    .input(usersBase)
    .input(ArrowEndpoint.header("Authorization", Codec.listFirst(Codec.string)))
    .input(ArrowEndpoint.fixedPath("u"))
    .input(ArrowEndpoint.anyJsonBody(CreateUser.jsonCodec))
    .output(
      ArrowEndpoint.anyJsonBody(User.jsonCodec)
        .description("Create new user")
    )
    .output(ArrowEndpoint.statusCode(StatusCode.Created))
    .errorOutput(EndpointOutput.OneOf(errorOutput, Codec.idPlain()))

public val dbError: Endpoint<Unit, ErrorResponse, List<User>> =
  Endpoint.get()
    .input(usersBase)
    .input(ArrowEndpoint.fixedPath("db"))
    .output(
      ArrowEndpoint.anyJsonBody(User.jsonCodecAsList)
        .description("Find all users")
        .default(emptyList())
        .example(emptyList())
    )
    .output(ArrowEndpoint.statusCode(StatusCode.Created))
    .errorOutput(EndpointOutput.OneOf(errorOutput, Codec.idPlain()))

public val badRequestError: Endpoint<Unit, ErrorResponse, List<User>> =
  Endpoint.get()
    .input(usersBase)
    .input(ArrowEndpoint.fixedPath("bad"))
    .output(
      ArrowEndpoint.anyJsonBody(User.jsonCodecAsList)
        .description("Find all users")
        .default(emptyList())
        .example(emptyList())
    )
    .output(ArrowEndpoint.statusCode(StatusCode.Created))
    .errorOutput(EndpointOutput.OneOf(errorOutput, Codec.idPlain()))

@Serializable
public data class ApiErrors(val errors: List<ApiError>) {
  public companion object {
    public val schema: Schema<ApiErrors> = Schema.product(
      ApiErrors::errors to ApiError.schema.asList()
    )
    public val jsonCodec: JsonCodec<ApiErrors> = Codec.kotlinxJson(schema)
  }
}

@Serializable
public data class ErrorResponse(val error: ApiError) {
  public companion object {
    public val schema: Schema<ErrorResponse> = Schema.product(
      ErrorResponse::error to ApiError.schema,
    )
    public val jsonCodec: JsonCodec<ErrorResponse> = Codec.kotlinxJson(schema)
  }
}

@Serializable
public data class ApiError(val code: ErrorCode, val message: String) {
  public companion object {
    public val schema: Schema<ApiError> = Schema.product(
      ApiError::code to ErrorCode.schema,
      ApiError::message to Schema.string,
    )
    public val jsonCodec: JsonCodec<ApiError> = Codec.kotlinxJson(schema)
  }
}

@Serializable
public enum class ErrorCode {
  @SerialName("database_error")
  DatabaseError,

  @SerialName("bad_request_error")
  BadRequestError,

  @SerialName("unauthorized_error")
  UnauthorizedError;

  public fun statusCode(): StatusCode = when (this) {
    DatabaseError -> StatusCode.InternalServerError
    BadRequestError -> StatusCode.BadRequest
    UnauthorizedError -> StatusCode.Unauthorized
  }

  public companion object {
    public val schema: Schema<ErrorCode> = Schema.enum()
    public val jsonCodec: JsonCodec<ErrorCode> = Codec.kotlinxJson(schema)
  }
}

public fun endpoints(): List<ServerEndpoint<*, *, *>> = listOf<ServerEndpoint<*, *, *>>(
  ServerEndpoint(createUser) {
    if (it.first == "auth") {
      User(it.second.id, "t", "t", "e", "a").right()
    } else {
      ErrorResponse(
        ApiError(
          ErrorCode.UnauthorizedError,
          "authorized error"
        )
      ).left()
    }
  },
  ServerEndpoint(findAll) { listOf(User(1L, "t", "t", "e", "a")).right() },
  ServerEndpoint(findById) { User(1L, "t", "t", "e", "a").right() },
  ServerEndpoint(dbError) {
    ErrorResponse(
      ApiError(
        ErrorCode.DatabaseError,
        "db error"
      )
    ).left()
  },
  ServerEndpoint(badRequestError) {
    ErrorResponse(
      ApiError(
        ErrorCode.BadRequestError,
        "bad request"
      )
    ).left()
  },
)
