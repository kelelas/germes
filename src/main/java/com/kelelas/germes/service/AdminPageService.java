package com.kelelas.germes.service;

import com.kelelas.germes.config.ConstantBundle;
import com.kelelas.germes.entity.Order;
import com.kelelas.germes.entity.History;
import com.kelelas.germes.exception.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class AdminPageService {
    HistoryService historyService;
    OrderService orderService;
    BillService billService;
@Autowired
    public AdminPageService(HistoryService historyService, OrderService orderService, BillService billService) {
        this.historyService = historyService;
        this.orderService = orderService;
        this.billService = billService;
    }


    @Transactional
    public void updateStoryById(String id) throws Exception {
        try {
            History historyItem = historyService.getStoryById(Long.valueOf(id));
            if (historyItem.getStatusId() == ConstantBundle.getIntProperty("status.waitingForConfirm")) {
                historyItem.setStatusId((long) ConstantBundle.getIntProperty("status.newOrder"));
                billService.saveNewBill(historyItem);
                historyService.update(historyItem);
            }
        }catch (Exception e){
            throw new DBException(e);
        }

    }
}
