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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.production.controller.query;

import com.occulue.api.*;
import com.occulue.controller.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Implements Spring Controller query CQRS processing for entity GeneratingUnit.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/GeneratingUnit")
public class GeneratingUnitQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a GeneratingUnit using a UUID
   *
   * @param UUID generatingUnitId
   * @return GeneratingUnit
   */
  @GetMapping("/load")
  public GeneratingUnit load(@RequestParam(required = true) UUID generatingUnitId) {
    GeneratingUnit entity = null;

    try {
      entity =
          GeneratingUnitBusinessDelegate.getGeneratingUnitInstance()
              .getGeneratingUnit(new GeneratingUnitFetchOneSummary(generatingUnitId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load GeneratingUnit using Id " + generatingUnitId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all GeneratingUnit business objects
   *
   * @return Set<GeneratingUnit>
   */
  @GetMapping("/")
  public List<GeneratingUnit> loadAll() {
    List<GeneratingUnit> generatingUnitList = null;

    try {
      // load the GeneratingUnit
      generatingUnitList =
          GeneratingUnitBusinessDelegate.getGeneratingUnitInstance().getAllGeneratingUnit();

      if (generatingUnitList != null)
        LOGGER.log(Level.INFO, "successfully loaded all GeneratingUnits");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all GeneratingUnits ", exc);
      return null;
    }

    return generatingUnitList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected GeneratingUnit generatingUnit = null;
  private static final Logger LOGGER =
      Logger.getLogger(GeneratingUnitQueryRestController.class.getName());
}
