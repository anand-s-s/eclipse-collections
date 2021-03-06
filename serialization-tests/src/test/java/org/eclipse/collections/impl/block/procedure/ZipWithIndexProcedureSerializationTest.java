/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import java.util.Collection;

import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ZipWithIndexProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5aaXBX\n"
                        + "aXRoSW5kZXhQcm9jZWR1cmUAAAAAAAAAAQIAAkkABWluZGV4TAAGdGFyZ2V0dAAWTGphdmEvdXRp\n"
                        + "bC9Db2xsZWN0aW9uO3hwAAAAAHA=",
                new ZipWithIndexProcedure<Object, Collection<Pair<Object, Integer>>>(null));
    }
}
