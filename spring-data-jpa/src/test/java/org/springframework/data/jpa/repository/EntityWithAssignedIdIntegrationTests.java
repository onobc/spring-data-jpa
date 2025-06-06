/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.jpa.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.sample.EntityWithAssignedId;
import org.springframework.data.jpa.repository.sample.EntityWithAssignedIdRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Oliver Drotbohm
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:config/namespace-application-context.xml")
@Transactional
class EntityWithAssignedIdIntegrationTests {

	@Autowired EntityWithAssignedIdRepository repository;

	@Test // DATAJPA-1535
	void deletesEntityJustCreated() {

		EntityWithAssignedId entityWithAssignedId = repository.save(new EntityWithAssignedId());

		repository.deleteById(entityWithAssignedId.getId());

		assertThat(repository.existsById(entityWithAssignedId.getId())).isFalse();
	}
}
