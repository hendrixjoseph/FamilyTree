/*
 *  The MIT License (MIT)
 *
 *  View the full license at:
 *  https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
 *
 *  Copyright (c) 2015 Joseph Hendrix
 *
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *
 */

package edu.wright.hendrix11.familyTree.faces;

import org.primefaces.component.chart.Chart;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

/**
 * @author Joe Hendrix
 */
@FacesComponent
@ResourceDependencies({
                              @ResourceDependency(library="primefaces", name="charts/charts.css"),
                              @ResourceDependency(library="primefaces", name="jquery/jquery.js"),
                              @ResourceDependency(library="primefaces", name="primefaces.js"),
                              @ResourceDependency(library="primefaces", name="charts/charts.js")
                      })
public class CustomChart extends Chart
{
}
