/*
 * Copyright 2016 Nailton Andrade.
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
package br.com.semanticwot.cd.discovery.infra;

import java.nio.ByteBuffer;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Hash {

	public static String hash(Object entity) {
		int hashCode = HashCodeBuilder.reflectionHashCode(entity);

		byte[] array = ByteBuffer.allocate(4).putInt(hashCode).array();

		String hash = Hex.encodeHexString(array);
		return hash;

	}
	

}
