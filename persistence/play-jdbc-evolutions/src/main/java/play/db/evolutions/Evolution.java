/*
 * Copyright (C) Lightbend Inc. <https://www.lightbend.com>
 */

package play.db.evolutions;

/** An evolution. */
public final class Evolution {
  private final int revision;
  private final String sqlUp;
  private final String sqlDown;
  private final String state;

  /**
   * Create the evolution.
   *
   * @param revision The revision of the evolution to create.
   * @param sqlUp The SQL script for bringing the evolution up.
   * @param sqlDown The SQL script for tearing the evolution down.
   * @param state
   */
  public Evolution(int revision, String sqlUp, String sqlDown, String state) {
    this.revision = revision;
    this.sqlUp = sqlUp;
    this.sqlDown = sqlDown;
    this.state = state;
  }

  /**
   * Get the revision of the evolution.
   *
   * @return The revision of the evolution to create.
   */
  public int getRevision() {
    return revision;
  }

  /**
   * Get the SQL script for bringing the evolution up.
   *
   * @return the sql script.
   */
  public String getSqlUp() {
    return sqlUp;
  }

  /**
   * Get the SQL script for tearing the evolution down.
   *
   * @return the sql script.
   */
  public String getSqlDown() {
    return sqlDown;
  }

  public String getState() {
    return state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Evolution evolution = (Evolution) o;

    if (revision != evolution.revision) return false;
    if (sqlDown != null ? !sqlDown.equals(evolution.sqlDown) : evolution.sqlDown != null)
      return false;
    if (sqlUp != null ? !sqlUp.equals(evolution.sqlUp) : evolution.sqlUp != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = revision;
    result = 31 * result + (sqlUp != null ? sqlUp.hashCode() : 0);
    result = 31 * result + (sqlDown != null ? sqlDown.hashCode() : 0);
    return result;
  }
}
