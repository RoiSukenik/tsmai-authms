package org.tsmai.authms.persistence.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository

@NoRepositoryBean
interface IBaseRepository<T, ID> : CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>