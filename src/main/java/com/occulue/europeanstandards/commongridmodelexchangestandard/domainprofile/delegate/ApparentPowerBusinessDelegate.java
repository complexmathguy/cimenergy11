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
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.delegate;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.validator.*;
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
 * ApparentPower business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of ApparentPower related services in the case of a ApparentPower
 *       business related service failing.
 *   <li>Exposes a simpler, uniform ApparentPower interface to the business tier, making it easy for
 *       clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill ApparentPower business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class ApparentPowerBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public ApparentPowerBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * ApparentPower Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return ApparentPowerBusinessDelegate
   */
  public static ApparentPowerBusinessDelegate getApparentPowerInstance() {
    return (new ApparentPowerBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createApparentPower(CreateApparentPowerCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getApparentPowerId() == null) command.setApparentPowerId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      ApparentPowerValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateApparentPowerCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateApparentPowerCommand of ApparentPower is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create ApparentPower - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateApparentPowerCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateApparentPower(UpdateApparentPowerCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      ApparentPowerValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateApparentPowerCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save ApparentPower - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteApparentPowerCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteApparentPowerCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ApparentPowerValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteApparentPowerCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete ApparentPower using Id = " + command.getApparentPowerId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the ApparentPower via ApparentPowerFetchOneSummary
   *
   * @param summary ApparentPowerFetchOneSummary
   * @return ApparentPowerFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public ApparentPower getApparentPower(ApparentPowerFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("ApparentPowerFetchOneSummary arg cannot be null");

    ApparentPower entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      ApparentPowerValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a ApparentPower
      // --------------------------------------
      CompletableFuture<ApparentPower> futureEntity =
          queryGateway.query(
              new FindApparentPowerQuery(new LoadApparentPowerFilter(summary.getApparentPowerId())),
              ResponseTypes.instanceOf(ApparentPower.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate ApparentPower with id " + summary.getApparentPowerId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all ApparentPowers
   *
   * @return List<ApparentPower>
   * @exception ProcessingException Thrown if any problems
   */
  public List<ApparentPower> getAllApparentPower() throws ProcessingException {
    List<ApparentPower> list = null;

    try {
      CompletableFuture<List<ApparentPower>> futureList =
          queryGateway.query(
              new FindAllApparentPowerQuery(),
              ResponseTypes.multipleInstancesOf(ApparentPower.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all ApparentPower";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign Value on ApparentPower
   *
   * @param command AssignValueToApparentPowerCommand
   * @exception ProcessingException
   */
  public void assignValue(AssignValueToApparentPowerCommand command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getApparentPowerId());

    FloatProxyBusinessDelegate childDelegate = FloatProxyBusinessDelegate.getFloatProxyInstance();
    ApparentPowerBusinessDelegate parentDelegate =
        ApparentPowerBusinessDelegate.getApparentPowerInstance();
    UUID childId = command.getAssignment().getFloatProxyId();
    FloatProxy child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      ApparentPowerValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get FloatProxy using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Value on ApparentPower
   *
   * @param command UnAssignValueFromApparentPowerCommand
   * @exception ProcessingException
   */
  public void unAssignValue(UnAssignValueFromApparentPowerCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ApparentPowerValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Value on ApparentPower";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return ApparentPower
   */
  private ApparentPower load(UUID id) throws ProcessingException {
    apparentPower =
        ApparentPowerBusinessDelegate.getApparentPowerInstance()
            .getApparentPower(new ApparentPowerFetchOneSummary(id));
    return apparentPower;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private ApparentPower apparentPower = null;
  private static final Logger LOGGER =
      Logger.getLogger(ApparentPowerBusinessDelegate.class.getName());
}
