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

package io.seata.samples.dubbo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * The type Order.
 */
public class Account implements Serializable, RowMapper<Account> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9187977528272637949L;
	/**
     * The Id.
     */
    public long id;
    /**
     * The User id.
     */
    public String userId;
    /**
     * The Money.
     */
    public int money;

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", money=" + money +
            '}';
    }

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.id = rs.getLong("id");
		account.userId = rs.getString("user_id");
		account.money = rs.getInt("money");
		
		return account;
	}
}
