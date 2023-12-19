package com.antoninovitale.beerpedia.data.local

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object BeerListEntitySerializer : Serializer<BeerListEntity> {

    override val defaultValue: BeerListEntity =
        BeerListEntity.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): BeerListEntity =
        try {
            BeerListEntity.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: BeerListEntity, output: OutputStream) =
        t.writeTo(output)
}
