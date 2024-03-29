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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.operationallimits.delegate;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.operationallimits.validator.*;
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
 * VoltageLimit business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of VoltageLimit related services in the case of a VoltageLimit
 *       business related service failing.
 *   <li>Exposes a simpler, uniform VoltageLimit interface to the business tier, making it easy for
 *       clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill VoltageLimit business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class VoltageLimitBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public VoltageLimitBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * VoltageLimit Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return VoltageLimitBusinessDelegate
   */
  public static VoltageLimitBusinessDelegate getVoltageLimitInstance() {
    return (new VoltageLimitBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createVoltageLimit(CreateVoltageLimitCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getVoltageLimitId() == null) command.setVoltageLimitId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      VoltageLimitValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateVoltageLimitCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateVoltageLimitCommand of VoltageLimit is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create VoltageLimit - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateVoltageLimitCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateVoltageLimit(UpdateVoltageLimitCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      VoltageLimitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateVoltageLimitCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save VoltageLimit - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteVoltageLimitCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteVoltageLimitCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      VoltageLimitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteVoltageLimitCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete VoltageLimit using Id = " + command.getVoltageLimitId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the VoltageLimit via VoltageLimitFetchOneSummary
   *
   * @param summary VoltageLimitFetchOneSummary
   * @return VoltageLimitFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public VoltageLimit getVoltageLimit(VoltageLimitFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("VoltageLimitFetchOneSummary arg cannot be null");

    VoltageLimit entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      VoltageLimitValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a VoltageLimit
      // --------------------------------------
      CompletableFuture<VoltageLimit> futureEntity =
          queryGateway.query(
              new FindVoltageLimitQuery(new LoadVoltageLimitFilter(summary.getVoltageLimitId())),
              ResponseTypes.instanceOf(VoltageLimit.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg = "Unable to locate VoltageLimit with id " + summary.getVoltageLimitId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all VoltageLimits
   *
   * @return List<VoltageLimit>
   * @exception ProcessingException Thrown if any problems
   */
  public List<VoltageLimit> getAllVoltageLimit() throws ProcessingException {
    List<VoltageLimit> list = null;

    try {
      CompletableFuture<List<VoltageLimit>> futureList =
          queryGateway.query(
              new FindAllVoltageLimitQuery(),
              ResponseTypes.multipleInstancesOf(VoltageLimit.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all VoltageLimit";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign Value on VoltageLimit
   *
   * @param command AssignValueToVoltageLimitCommand
   * @exception ProcessingException
   */
  public void assignValue(AssignValueToVoltageLimitCommand command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getVoltageLimitId());

    VoltageBusinessDelegate childDelegate = VoltageBusinessDelegate.getVoltageInstance();
    VoltageLimitBusinessDelegate parentDelegate =
        VoltageLimitBusinessDelegate.getVoltageLimitInstance();
    UUID childId = command.getAssignment().getVoltageId();
    Voltage child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      VoltageLimitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get Voltage using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Value on VoltageLimit
   *
   * @param command UnAssignValueFromVoltageLimitCommand
   * @exception ProcessingException
   */
  public void unAssignValue(UnAssignValueFromVoltageLimitCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      VoltageLimitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Value on VoltageLimit";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return VoltageLimit
   */
  private VoltageLimit load(UUID id) throws ProcessingException {
    voltageLimit =
        VoltageLimitBusinessDelegate.getVoltageLimitInstance()
            .getVoltageLimit(new VoltageLimitFetchOneSummary(id));
    return voltageLimit;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private VoltageLimit voltageLimit = null;
  private static final Logger LOGGER =
      Logger.getLogger(VoltageLimitBusinessDelegate.class.getName());
}
