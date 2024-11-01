package com.example.dataanalysergrpcmicroservice.service;

import com.example.dataanalysergrpcmicroservice.model.Data;
import com.example.dataanalysergrpcmicroservice.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
 class DataServiceImpl  implements DataService {

    private final DataRepository dataRepository;

    @Override
    public void handle (Data data) {
        log.info("Data object {} was saved", data);
    }

    @Override
    @Transactional
    public List<Data> getWithBatch(long batchSize) {
        List<Data> data = dataRepository.findAllWithOffset(batchSize);
        if(data.size() > 0) {
            dataRepository.incrementOffset(Long.min(batchSize, data.size()));
        }
        return data;
    }

}
