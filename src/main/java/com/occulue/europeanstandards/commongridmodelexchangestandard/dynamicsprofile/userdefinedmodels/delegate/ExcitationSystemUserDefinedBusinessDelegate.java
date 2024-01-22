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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels.delegate;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels.validator.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

/**
 * ExcitationSystemUserDefined business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of ExcitationSystemUserDefined related services in the case of a
 *       ExcitationSystemUserDefined business related service failing.
 *   <li>Exposes a simpler, uniform ExcitationSystemUserDefined interface to the business tier,
 *       making it easy for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill
 *       ExcitationSystemUserDefined business related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class ExcitationSystemUserDefinedBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public ExcitationSystemUserDefinedBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * ExcitationSystemUserDefined Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return ExcitationSystemUserDefinedBusinessDelegate
   */
  public static ExcitationSystemUserDefinedBusinessDelegate
      getExcitationSystemUserDefinedInstance() {
    return (new ExcitationSystemUserDefinedBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createExcitationSystemUserDefined(
      CreateExcitationSystemUserDefinedCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getExcitationSystemUserDefinedId() == null)
        command.setExcitationSystemUserDefinedId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      ExcitationSystemUserDefinedValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateExcitationSystemUserDefinedCommand - by convention the future return value
      // for a create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateExcitationSystemUserDefinedCommand of ExcitationSystemUserDefined is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create ExcitationSystemUserDefined - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateExcitationSystemUserDefinedCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateExcitationSystemUserDefined(
      UpdateExcitationSystemUserDefinedCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      ExcitationSystemUserDefinedValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateExcitationSystemUserDefinedCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save ExcitationSystemUserDefined - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteExcitationSystemUserDefinedCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteExcitationSystemUserDefinedCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ExcitationSystemUserDefinedValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteExcitationSystemUserDefinedCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete ExcitationSystemUserDefined using Id = "
              + command.getExcitationSystemUserDefinedId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the ExcitationSystemUserDefined via
   * ExcitationSystemUserDefinedFetchOneSummary
   *
   * @param summary ExcitationSystemUserDefinedFetchOneSummary
   * @return ExcitationSystemUserDefinedFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public ExcitationSystemUserDefined getExcitationSystemUserDefined(
      ExcitationSystemUserDefinedFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException(
          "ExcitationSystemUserDefinedFetchOneSummary arg cannot be null");

    ExcitationSystemUserDefined entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      ExcitationSystemUserDefinedValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a ExcitationSystemUserDefined
      // --------------------------------------
      CompletableFuture<ExcitationSystemUserDefined> futureEntity =
          queryGateway.query(
              new FindExcitationSystemUserDefinedQuery(
                  new LoadExcitationSystemUserDefinedFilter(
                      summary.getExcitationSystemUserDefinedId())),
              ResponseTypes.instanceOf(ExcitationSystemUserDefined.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate ExcitationSystemUserDefined with id "
              + summary.getExcitationSystemUserDefinedId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all ExcitationSystemUserDefineds
   *
   * @return List<ExcitationSystemUserDefined>
   * @exception ProcessingException Thrown if any problems
   */
  public List<ExcitationSystemUserDefined> getAllExcitationSystemUserDefined()
      throws ProcessingException {
    List<ExcitationSystemUserDefined> list = null;

    try {
      CompletableFuture<List<ExcitationSystemUserDefined>> futureList =
          queryGateway.query(
              new FindAllExcitationSystemUserDefinedQuery(),
              ResponseTypes.multipleInstancesOf(ExcitationSystemUserDefined.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all ExcitationSystemUserDefined";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign Proprietary on ExcitationSystemUserDefined
   *
   * @param command AssignProprietaryToExcitationSystemUserDefinedCommand
   * @exception ProcessingException
   */
  public void assignProprietary(AssignProprietaryToExcitationSystemUserDefinedCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getExcitationSystemUserDefinedId());

    BooleanProxyBusinessDelegate childDelegate =
        BooleanProxyBusinessDelegate.getBooleanProxyInstance();
    ExcitationSystemUserDefinedBusinessDelegate parentDelegate =
        ExcitationSystemUserDefinedBusinessDelegate.getExcitationSystemUserDefinedInstance();
    UUID childId = command.getAssignment().getBooleanProxyId();
    BooleanProxy child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      ExcitationSystemUserDefinedValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get BooleanProxy using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Proprietary on ExcitationSystemUserDefined
   *
   * @param command UnAssignProprietaryFromExcitationSystemUserDefinedCommand
   * @exception ProcessingException
   */
  public void unAssignProprietary(UnAssignProprietaryFromExcitationSystemUserDefinedCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ExcitationSystemUserDefinedValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Proprietary on ExcitationSystemUserDefined";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return ExcitationSystemUserDefined
   */
  private ExcitationSystemUserDefined load(UUID id) throws ProcessingException {
    excitationSystemUserDefined =
        ExcitationSystemUserDefinedBusinessDelegate.getExcitationSystemUserDefinedInstance()
            .getExcitationSystemUserDefined(new ExcitationSystemUserDefinedFetchOneSummary(id));
    return excitationSystemUserDefined;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private ExcitationSystemUserDefined excitationSystemUserDefined = null;
  private static final Logger LOGGER =
      Logger.getLogger(ExcitationSystemUserDefinedBusinessDelegate.class.getName());
}
