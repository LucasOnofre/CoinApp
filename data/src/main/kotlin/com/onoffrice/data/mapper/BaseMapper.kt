package com.onoffrice.data.mapper

interface BaseMapper<RESPONSE, DOMAIN> {

    fun mapFromDomain(domain: DOMAIN): RESPONSE

    fun mapToDomain(response: RESPONSE): DOMAIN
}