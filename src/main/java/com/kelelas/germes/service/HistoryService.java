package com.kelelas.germes.service;



import com.kelelas.germes.dto.HistoryDTO;
import com.kelelas.germes.entity.History;
import com.kelelas.germes.exception.DBException;
import com.kelelas.germes.mapper.LocaleHistoryMapper;
import com.kelelas.germes.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class HistoryService {
    HistoryRepository historyRepository;
    LocaleHistoryMapper mapper;
    @Autowired
    public HistoryService(HistoryRepository historyRepository, LocaleHistoryMapper mapper) {
        this.historyRepository = historyRepository;
        this.mapper = mapper;
    }

    public History getStoryById(Long id){
        return historyRepository.findById(id).orElseThrow(DBException::new);
    }

    public void save(History history){
        historyRepository.save(history);
    }

    public void update(History history){
        history.setDate(LocalDateTime.now());
        historyRepository.save(history);}

    public List<HistoryDTO> getLocaleStoriesByUserId( Long userId){
        return historyRepository.findAllByUserId(userId).stream().map(mapper::dtoMapper)
                .sorted(Comparator.comparingLong(HistoryDTO::getId)).collect(Collectors.toList());
    }

    public List<HistoryDTO> getLocaleStoriesByStatus(Long statusId){
        return historyRepository.findAllByStatusId(statusId).stream().map(mapper::dtoMapper)
                .sorted(Comparator.comparingLong(HistoryDTO::getId)).collect(Collectors.toList());   }

    public List<HistoryDTO> getLocaleStoriesByStatusAndUserId(Long statusId,  Long userId){
        return historyRepository.findAllByStatusIdAndUserId(statusId, userId).stream().map(mapper::dtoMapper).collect(Collectors.toList());   }

        public List<HistoryDTO> getLocaleStories(){
        return historyRepository.findAll().stream().map(mapper::dtoMapper).collect(Collectors.toList());   }


        //todo pagenation
//    public List<HistoryDTO> getLocaleStories(HttpServletRequest request, int offset, int amountOfRecords){
//        return historyRepository.getLocaleStories(request.getSession().getAttribute("lang").toString(), offset, amountOfRecords);
//    }
//
//    public int numberOfRowsInTable(){
//        return historyRepository.numberOfRowsInTable();
//    }
}
