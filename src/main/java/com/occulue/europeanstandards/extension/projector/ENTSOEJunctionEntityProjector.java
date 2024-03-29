/**
 * ***************************************************************************** Turnstone Biologics
 * Confidential
 *
 * <p>2018 Turnstone Biologics All Rights Reserved.
 *
 * <p>This file is subject to the terms and conditions defined in file 'license.txt', which is part
 * of this source code package.
 *
 * <p>Contributors : Turnstone Biologics - General Release
 * ****************************************************************************
 */
package com.occulue.europeanstandards.extension.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.extension.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for ENTSOEJunction as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by ENTSOEJunctionAggregate
 *
 * @author your_name_here
 */
@Component("eNTSOEJunction-entity-projector")
public class ENTSOEJunctionEntityProjector {

  // core constructor
  public ENTSOEJunctionEntityProjector(ENTSOEJunctionRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a ENTSOEJunction
   *
   * @param	entity ENTSOEJunction
   */
  public ENTSOEJunction create(ENTSOEJunction entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a ENTSOEJunction
   *
   * @param	entity ENTSOEJunction
   */
  public ENTSOEJunction update(ENTSOEJunction entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a ENTSOEJunction
   *
   * @param	id		UUID
   */
  public ENTSOEJunction delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    ENTSOEJunction entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /**
   * Method to retrieve the ENTSOEJunction via an FindENTSOEJunctionQuery
   *
   * @return query FindENTSOEJunctionQuery
   */
  @SuppressWarnings("unused")
  public ENTSOEJunction find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a ENTSOEJunction - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all ENTSOEJunctions
   *
   * @param query FindAllENTSOEJunctionQuery
   * @return List<ENTSOEJunction>
   */
  @SuppressWarnings("unused")
  public List<ENTSOEJunction> findAll(FindAllENTSOEJunctionQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all ENTSOEJunction - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final ENTSOEJunctionRepository repository;

  private static final Logger LOGGER =
      Logger.getLogger(ENTSOEJunctionEntityProjector.class.getName());
}
