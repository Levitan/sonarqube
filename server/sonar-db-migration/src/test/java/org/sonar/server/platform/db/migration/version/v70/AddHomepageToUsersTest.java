/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.platform.db.migration.version.v70;

import java.sql.SQLException;
import java.sql.Types;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sonar.db.CoreDbTester;

import static java.sql.Types.BOOLEAN;
import static java.sql.Types.VARCHAR;
import static org.junit.rules.ExpectedException.none;
import static org.sonar.db.CoreDbTester.createForSchema;

public class AddHomepageToUsersTest {

  @Rule
  public final CoreDbTester dbTester = createForSchema(AddHomepageToUsersTest.class, "initial.sql");

  @Rule
  public ExpectedException expectedException = none();

  private AddHomepageToUsers underTest = new AddHomepageToUsers(dbTester.database());

  @Test
  public void column_is_added_to_table() throws SQLException {
    underTest.execute();

    dbTester.assertColumnDefinition("users", "homepage_type", VARCHAR, 40, true);
    dbTester.assertColumnDefinition("users", "homepage_key", VARCHAR, 64, true);
  }

  @Test
  public void migration_is_not_reentrant() throws SQLException {
    underTest.execute();

    expectedException.expect(IllegalStateException.class);

    underTest.execute();
  }

}