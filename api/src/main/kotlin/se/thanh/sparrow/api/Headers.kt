package se.thanh.sparrow.api

import arrow.endpoint.ArrowEndpoint
import arrow.endpoint.Codec
import arrow.endpoint.JsonCodec
import arrow.endpoint.PlainCodec
import arrow.endpoint.Schema
import arrow.endpoint.product
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class Token(val value: String) {
  companion object {
    val schema: Schema<Token> = Schema.product(
      Token::value to Schema.string,
    )
    val jsonCodec: JsonCodec<Token> = Codec.kotlinxJson(schema)
    val plainCodec: PlainCodec<Token> = Codec.stringCodec(Schema.string()) { Token(it) }
  }
}

val authorizationHeader = ArrowEndpoint.header("Authorization", Codec.listFirstOrNull(Token.plainCodec))
