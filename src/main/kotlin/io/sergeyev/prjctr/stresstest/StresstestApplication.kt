package io.sergeyev.prjctr.stresstest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.data.cassandra.core.query.CassandraPageRequest
import org.springframework.data.cassandra.repository.Query
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@SpringBootApplication
class StresstestApplication {

}

fun main(args: Array<String>) {
    runApplication<StresstestApplication>(*args)
}

@RestController
class StressTestController(
        val recordsRepository: RecordsRepository
) {


    @GetMapping("/records")
    fun findRecords (): Flux<Record> {
        return recordsRepository.findAll()
    }

    @PostMapping("/records")
    fun saveRecord(): Mono<Record> {
        return recordsRepository.save(Record(UUID.randomUUID().toString(), System.currentTimeMillis()))
    }

}


@Repository
interface RecordsRepository : ReactiveCassandraRepository<Record, String>


@Table
data class Record (
        @PrimaryKey @Id
        val id: String,
        val createdAt: Long

)
