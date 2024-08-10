package org.tsmai.authms.persistence.repositories

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.data.repository.reactive.ReactiveSortingRepository

@NoRepositoryBean
interface IBaseRepository<T, ID> : ReactiveCrudRepository<T, ID>, ReactiveSortingRepository<T, ID>