package ua.khpi.analysis;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;

import ua.khpi.analysis.beans.ArchiMateElement;

public class ArchiMateModelAnalysis {

	private Model archiMateModel;
	private List<ArchiMateElement> archiMateElements;

	public ArchiMateModelAnalysis(Model graph) {
		super();
		this.archiMateModel = graph;
	}

	public List<ArchiMateElement> getArchiMateModelElements() {
		if (archiMateElements == null) {
			archiMateElements = new ArrayList<ArchiMateElement>();

			for (ResIterator iterator = archiMateModel.listSubjects(); iterator.hasNext();) {
				Resource resource = iterator.nextResource();
				int propertiesNumber = 0;

				for (StmtIterator j = resource.listProperties(); j.hasNext();) {
					j.nextStatement();
					propertiesNumber++;
				}

				archiMateElements.add(new ArchiMateElement(resource.getLocalName(), propertiesNumber));
			}
		}

		return archiMateElements;
	}

	public double calculateArchiMateModelDensity() {
		if (archiMateElements == null) {
			archiMateElements = getArchiMateModelElements();
		}

		double archiMateModelSize = archiMateElements.size();
		double archiMateElementsPropertiesSum = 0;

		for (ArchiMateElement archiMateElement : archiMateElements) {
			archiMateElementsPropertiesSum += archiMateElement.getElementPropertiesNumber();
		}

		double archiMateModelDensity = archiMateElementsPropertiesSum / (archiMateModelSize * (archiMateModelSize - 1));

		return archiMateModelDensity;
	}

	public void calculateArchiMateElementsCentrality() {
		if (archiMateElements == null) {
			archiMateElements = getArchiMateModelElements();
		}

		double archiMateModelSize = archiMateElements.size();

		for (ArchiMateElement archiMateElement : archiMateElements) {
			double archiMateElementCentrality = archiMateElement.getElementPropertiesNumber() / (archiMateModelSize - 1);

			archiMateElement.setElementCentrality(archiMateElementCentrality);
		}
	}

	public double calculateArchiMateModelCentrality() {
		if (archiMateElements == null) {
			archiMateElements = getArchiMateModelElements();
		}

		double archiMateModelSize = archiMateElements.size();
		double archiMateElementPropertiesNumberDeviation = 0;
		double maxArchiMateElementPropertiesNumber = getMaxArchiMateElementPropertiesNumber();

		for (ArchiMateElement archiMateElement : archiMateElements) {
			archiMateElementPropertiesNumberDeviation += (maxArchiMateElementPropertiesNumber
					- archiMateElement.getElementPropertiesNumber());
		}

		double archiMateModelCentrality = archiMateElementPropertiesNumberDeviation
				/ ((archiMateModelSize - 1) * (archiMateModelSize - 2));

		return archiMateModelCentrality;
	}

	private double getMaxArchiMateElementPropertiesNumber() {
		if (archiMateElements == null) {
			archiMateElements = getArchiMateModelElements();
		}

		double maxArchiMateElementPropertiesNumber = 0;

		for (ArchiMateElement archiMateElement : archiMateElements) {
			if (archiMateElement.getElementPropertiesNumber() > maxArchiMateElementPropertiesNumber) {
				maxArchiMateElementPropertiesNumber = archiMateElement.getElementPropertiesNumber();
			}
		}

		return maxArchiMateElementPropertiesNumber;
	}

	public Model getArchiMateModel() {
		return archiMateModel;
	}

	public void setArchiMateModel(Model graph) {
		this.archiMateModel = graph;
	}

	public List<ArchiMateElement> getArchiMateElements() {
		return archiMateElements;
	}

	public void setArchiMateElements(List<ArchiMateElement> archiMateElements) {
		this.archiMateElements = archiMateElements;
	}
}
