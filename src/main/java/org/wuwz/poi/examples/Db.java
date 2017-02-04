/**

 * Copyright (c) 2017, 吴汶泽 (wuwz@live.com).

 *

 * Licensed under the Apache License, Version 2.0 (the "License");

 * you may not use this file except in compliance with the License.

 * You may obtain a copy of the License at

 *

 *      http://www.apache.org/licenses/LICENSE-2.0

 *

 * Unless required by applicable law or agreed to in writing, software

 * distributed under the License is distributed on an "AS IS" BASIS,

 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

 * See the License for the specific language governing permissions and

 * limitations under the License.

 */
package org.wuwz.poi.examples;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 模拟数据库数据
 * @author wuwz
 */
public class Db {
	static final List<User> users = Lists.newArrayList();
	
	static {
		// 生成10条测试数据
		for (int i = 0; i < 10; i++) {
			User e = new User();
			e.setUid(i+1);
			e.setUsername("USERNAME_"+(i+1));
			e.setPassword("PASSWORD_"+(i+1));
			e.setNickname("NICKNAME_"+(i+1));
			e.setAge(18);
			users.add(e);
		}
	}

	
	public static List<User> getUsers() {
		return users;
	}
	
	public static void addUser(User user) {
		users.add(user);
	}
}
