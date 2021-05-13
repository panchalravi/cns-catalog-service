package com.example.catalogservice.persistence

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class PersistableEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @CreatedDate
    var createdDate: Long,

    @LastModifiedDate
    var lastModifiedDate: Long,

    @Version
    var version: Int
) {
    constructor(): this(-1,0,0,-1)
}