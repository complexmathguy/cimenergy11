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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for Disconnector as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by DisconnectorAggregate
 *
 * @author your_name_here
 */
@Component("disconnector-entity-projector")
public class DisconnectorEntityProjector {

  // core constructor
  public DisconnectorEntityProjector(DisconnectorRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a Disconnector
   *
   * @param	entity Disconnector
   */
  public Disconnector create(Disconnector entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a Disconnector
   *
   * @param	entity Disconnector
   */
  public Disconnector update(Disconnector entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a Disconnector
   *
   * @param	id		UUID
   */
  public Disconnector delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    Disconnector entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /**
   * Method to retrieve the Disconnector via an FindDisconnectorQuery
   *
   * @return query FindDisconnectorQuery
   */
  @SuppressWarnings("unused")
  public Disconnector find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a Disconnector - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all Disconnectors
   *
   * @param query FindAllDisconnectorQuery
   * @return List<Disconnector>
   */
  @SuppressWarnings("unused")
  public List<Disconnector> findAll(FindAllDisconnectorQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all Disconnector - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final DisconnectorRepository repository;

  private static final Logger LOGGER =
      Logger.getLogger(DisconnectorEntityProjector.class.getName());
}
