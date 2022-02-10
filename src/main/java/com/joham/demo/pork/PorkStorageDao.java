package com.joham.demo.pork;

import org.springframework.stereotype.Repository;

@Repository
public class PorkStorageDao {

    PorkStorage queryStore(){
        return new PorkStorage(1L, 100L);
    }
}
