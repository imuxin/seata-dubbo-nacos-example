/*
 *  Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.seata.samples.dubbo.service.impl;

import java.util.List;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.seata.samples.dubbo.Account;
import io.seata.samples.dubbo.Order;
import io.seata.samples.dubbo.Storage;
import io.seata.samples.dubbo.service.BusinessService;
import io.seata.samples.dubbo.service.AccountService;
import io.seata.samples.dubbo.service.OrderService;
import io.seata.samples.dubbo.service.StorageService;

/**
 * Please add the follow VM arguments:
 * <pre>
 *     -Djava.net.preferIPv4Stack=true
 * </pre>
 */
public class BusinessServiceImpl implements BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);

    private StorageService storageService;
    private OrderService orderService;
    private AccountService accountService;

    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-demo-tx")
    public void purchase(String userId, String commodityCode, int orderCount) {
        LOGGER.info("purchase begin ... xid: " + RootContext.getXID());
        storageService.deduct(commodityCode, orderCount);
        orderService.create(userId, commodityCode, orderCount);
        //throw new RuntimeException("xxx");

    }
    
    @Override
    public List<Account> listAccount() {
    	return accountService.list();
    }
    
    @Override
    public List<Order> listOrder() {
    	return orderService.list();
    }
    
    @Override
    public List<Storage> listStorage() {
    	return storageService.list();
    }

    /**
     * Sets storage service.
     *
     * @param storageService the storage service
     */
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Sets order service.
     *
     * @param orderService the order service
     */
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    /**
     * Sets account service.
     *
     * @param accountService the account service
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

}
