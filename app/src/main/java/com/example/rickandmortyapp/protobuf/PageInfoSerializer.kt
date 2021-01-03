package com.example.rickandmortyapp.protobuf

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.example.rickandmortyapp.ResponsePageInfo
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object PageInfoSerializer : Serializer<ResponsePageInfo> {

    override fun readFrom(input: InputStream): ResponsePageInfo {
        try {
            return ResponsePageInfo.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override fun writeTo(t: ResponsePageInfo, output: OutputStream) {
        t.writeTo(output)
    }

}