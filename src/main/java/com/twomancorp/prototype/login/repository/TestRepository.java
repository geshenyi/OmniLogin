package com.twomancorp.prototype.login.repository;

import com.twomancorp.prototype.login.model.TestModel;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ssge on 1/23/16.
 */
public interface TestRepository extends PagingAndSortingRepository<TestModel,ObjectId>{

}
