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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.production.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.production.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for HydroPowerPlant as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by HydroPowerPlantAggregate
 *
 * @author your_name_here
 */
@Component("hydroPowerPlant-entity-projector")
public class HydroPowerPlantEntityProjector {

  // core constructor
  public HydroPowerPlantEntityProjector(HydroPowerPlantRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a HydroPowerPlant
   *
   * @param	entity HydroPowerPlant
   */
  public HydroPowerPlant create(HydroPowerPlant entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a HydroPowerPlant
   *
   * @param	entity HydroPowerPlant
   */
  public HydroPowerPlant update(HydroPowerPlant entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a HydroPowerPlant
   *
   * @param	id		UUID
   */
  public HydroPowerPlant delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    HydroPowerPlant entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /**
   * Method to retrieve the HydroPowerPlant via an FindHydroPowerPlantQuery
   *
   * @return query FindHydroPowerPlantQuery
   */
  @SuppressWarnings("unused")
  public HydroPowerPlant find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a HydroPowerPlant - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all HydroPowerPlants
   *
   * @param query FindAllHydroPowerPlantQuery
   * @return List<HydroPowerPlant>
   */
  @SuppressWarnings("unused")
  public List<HydroPowerPlant> findAll(FindAllHydroPowerPlantQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all HydroPowerPlant - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final HydroPowerPlantRepository repository;

  private static final Logger LOGGER =
      Logger.getLogger(HydroPowerPlantEntityProjector.class.getName());
}
