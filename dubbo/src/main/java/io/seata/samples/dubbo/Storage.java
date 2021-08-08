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
public class Storage implements Serializable, RowMapper<Storage> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3442050639700676626L;
	/**
     * The Id.
     */
    public long id;
    /**
     * The Commodity code.
     */
    public String commodityCode;
    /**
     * The Count.
     */
    public int count;

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", commodityCode='" + commodityCode + '\'' +
            ", count=" + count +
            '}';
    }

	@Override
	public Storage mapRow(ResultSet rs, int rowNum) throws SQLException {
		Storage storage = new Storage();
		storage.id = rs.getLong("id");
		storage.commodityCode = rs.getString("commodity_code");
		storage.count = rs.getInt("count");

		return storage;
	}
}
