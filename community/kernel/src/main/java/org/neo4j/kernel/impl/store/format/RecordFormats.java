/*
 * Copyright (c) 2002-2016 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.impl.store.format;

import org.neo4j.helpers.Service;
import org.neo4j.kernel.impl.store.StoreType;
import org.neo4j.kernel.impl.store.record.DynamicRecord;
import org.neo4j.kernel.impl.store.record.LabelTokenRecord;
import org.neo4j.kernel.impl.store.record.NodeRecord;
import org.neo4j.kernel.impl.store.record.PropertyKeyTokenRecord;
import org.neo4j.kernel.impl.store.record.PropertyRecord;
import org.neo4j.kernel.impl.store.record.RelationshipGroupRecord;
import org.neo4j.kernel.impl.store.record.RelationshipRecord;
import org.neo4j.kernel.impl.store.record.RelationshipTypeTokenRecord;

public interface RecordFormats
{
    abstract class Factory extends Service
    {
        public Factory( String key, String... altKeys )
        {
            super( key, altKeys );
        }

        public abstract RecordFormats newInstance();
    }

    String storeVersion();

    RecordFormat<NodeRecord> node();

    RecordFormat<RelationshipGroupRecord> relationshipGroup();

    RecordFormat<RelationshipRecord> relationship();

    RecordFormat<PropertyRecord> property();

    RecordFormat<LabelTokenRecord> labelToken();

    RecordFormat<PropertyKeyTokenRecord> propertyKeyToken();

    RecordFormat<RelationshipTypeTokenRecord> relationshipTypeToken();

    RecordFormat<DynamicRecord> dynamic();

    boolean hasStore( StoreType store );

    /**
     * Use when comparing one format to another, for example for migration purposes.
     *
     * @return array of {@link Capability capabilities} for comparison.
     */
    Capability[] capabilities();

    /**
     * @param capability {@link Capability} to check for.
     * @return whether or not this format has a certain {@link Capability}.
     */
    boolean hasCapability( Capability capability );

    boolean hasSameCapabilities( RecordFormats other, int types );
}
