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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.loadmodel.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.loadmodel.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Logger;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for LoadGroup as outlined for the CQRS pattern. All event handling and query handling
 * related to LoadGroup are invoked here and dispersed as an event to be handled elsewhere.
 *
 * <p>Commands are handled by LoadGroupAggregate
 *
 * @author your_name_here
 */
// @ProcessingGroup("loadGroup")
@Component("loadGroup-projector")
public class LoadGroupProjector extends LoadGroupEntityProjector {

  // core constructor
  public LoadGroupProjector(LoadGroupRepository repository, QueryUpdateEmitter queryUpdateEmitter) {
    super(repository);
    this.queryUpdateEmitter = queryUpdateEmitter;
  }

  /*
   * @param	event CreateLoadGroupEvent
   */
  @EventHandler(payloadType = CreateLoadGroupEvent.class)
  public void handle(CreateLoadGroupEvent event) {
    LOGGER.info("handling CreateLoadGroupEvent - " + event);
    LoadGroup entity = new LoadGroup();
    entity.setLoadGroupId(event.getLoadGroupId());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    create(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllLoadGroup(entity);
  }

  /*
   * @param	event UpdateLoadGroupEvent
   */
  @EventHandler(payloadType = UpdateLoadGroupEvent.class)
  public void handle(UpdateLoadGroupEvent event) {
    LOGGER.info("handling UpdateLoadGroupEvent - " + event);

    LoadGroup entity = new LoadGroup();
    entity.setLoadGroupId(event.getLoadGroupId());
    entity.setLoadGroups(event.getLoadGroups());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    update(entity);

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindLoadGroup(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllLoadGroup(entity);
  }

  /*
   * @param	event DeleteLoadGroupEvent
   */
  @EventHandler(payloadType = DeleteLoadGroupEvent.class)
  public void handle(DeleteLoadGroupEvent event) {
    LOGGER.info("handling DeleteLoadGroupEvent - " + event);

    // ------------------------------------------
    // delete delegation
    // ------------------------------------------
    LoadGroup entity = delete(event.getLoadGroupId());

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllLoadGroup(entity);
  }

  /*
   * @param	event AssignLoadGroupsToLoadGroupEvent
   */
  @EventHandler(payloadType = AssignLoadGroupsToLoadGroupEvent.class)
  public void handle(AssignLoadGroupsToLoadGroupEvent event) {
    LOGGER.info("handling AssignLoadGroupsToLoadGroupEvent - " + event);

    // ------------------------------------------
    // delegate to addTo
    // ------------------------------------------
    LoadGroup entity = addToLoadGroups(event.getLoadGroupId(), event.getAddTo());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindLoadGroup(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllLoadGroup(entity);
  }

  /*
   * @param	event RemoveLoadGroupsFromLoadGroupEvent
   */
  @EventHandler(payloadType = RemoveLoadGroupsFromLoadGroupEvent.class)
  public void handle(RemoveLoadGroupsFromLoadGroupEvent event) {
    LOGGER.info("handling RemoveLoadGroupsFromLoadGroupEvent - " + event);

    LoadGroup entity = removeFromLoadGroups(event.getLoadGroupId(), event.getRemoveFrom());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindLoadGroup(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllLoadGroup(entity);
  }

  /**
   * Method to retrieve the LoadGroup via an LoadGroupPrimaryKey.
   *
   * @param id Long
   * @return LoadGroup
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public LoadGroup handle(FindLoadGroupQuery query)
      throws ProcessingException, IllegalArgumentException {
    return find(query.getFilter().getLoadGroupId());
  }

  /**
   * Method to retrieve a collection of all LoadGroups
   *
   * @param query FindAllLoadGroupQuery
   * @return List<LoadGroup>
   * @exception ProcessingException Thrown if any problems
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public List<LoadGroup> handle(FindAllLoadGroupQuery query) throws ProcessingException {
    return findAll(query);
  }

  /**
   * emit to subscription queries of type FindLoadGroup, but only if the id matches
   *
   * @param entity LoadGroup
   */
  protected void emitFindLoadGroup(LoadGroup entity) {
    LOGGER.info("handling emitFindLoadGroup");

    queryUpdateEmitter.emit(
        FindLoadGroupQuery.class,
        query -> query.getFilter().getLoadGroupId().equals(entity.getLoadGroupId()),
        entity);
  }

  /**
   * unconditionally emit to subscription queries of type FindAllLoadGroup
   *
   * @param entity LoadGroup
   */
  protected void emitFindAllLoadGroup(LoadGroup entity) {
    LOGGER.info("handling emitFindAllLoadGroup");

    queryUpdateEmitter.emit(FindAllLoadGroupQuery.class, query -> true, entity);
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired private final QueryUpdateEmitter queryUpdateEmitter;
  private static final Logger LOGGER = Logger.getLogger(LoadGroupProjector.class.getName());
}
