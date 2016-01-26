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
package br.com.semanticwot.cd.discovery.cache;

import java.lang.ref.SoftReference;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityCache {

	private Map<String, SoftReference<EntityDatePair>> objectCache;
	
	private static final long INTERVALO_CEGO = 1000L;

	public EntityCache() {
		this.objectCache = new ConcurrentHashMap<>();
	}

	public void put(String path, Object entity) {

		EntityDatePair pair = new EntityDatePair(entity, new Date());
		SoftReference<EntityDatePair> sr = new SoftReference<>(pair);
		this.objectCache.put(path, sr);
	}

	public boolean isUpdated(String path, Date since) {

		SoftReference<EntityDatePair> sr = objectCache.get(path);
		if (sr != null) { //a referência não foi inserida no mapa
			EntityDatePair pair = sr.get();
			if (pair == null) { // se a referência foi limpa pelo GC
				objectCache.remove(path);
				return true;
			}
			
			long tempoArmazenado = pair.getDate().getTime() / INTERVALO_CEGO;
			long tempoFornecido = since.getTime() / INTERVALO_CEGO;
			
			//se a data armazenada é posterior à data passada como parâmetro,
			//significa que o objeto foi alterado
			return tempoArmazenado > tempoFornecido; 
		}
		return true;

	}
        
        public Object getEntityDatePairEntity(String path){
            return objectCache.get(path).get().getEntity();
        }

	protected static class EntityDatePair {

		private Object entity;
		private Date date;

		public EntityDatePair(Object entity, Date date) {
			this.entity = entity;
			this.date = date;
		}

		public Object getEntity() {
			return entity;
		}

		public Date getDate() {
			return date;
		}

	}

}
