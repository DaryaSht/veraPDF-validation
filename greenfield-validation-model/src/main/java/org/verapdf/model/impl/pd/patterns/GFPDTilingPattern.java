package org.verapdf.model.impl.pd.patterns;

import org.verapdf.model.baselayer.Object;
import org.verapdf.model.impl.pd.GFPDContentStream;
import org.verapdf.model.impl.pd.util.PDResourcesHandler;
import org.verapdf.model.pdlayer.PDContentStream;
import org.verapdf.model.pdlayer.PDTilingPattern;
import org.verapdf.pd.PDResources;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Bezrukov
 */
public class GFPDTilingPattern extends GFPDPattern implements PDTilingPattern {

	public static final String TILING_PATTERN_TYPE = "PDTilingPattern";

	public static final String CONTENT_STREAM = "contentStream";

	private final PDResources inheritedResources;

	public GFPDTilingPattern(
			org.verapdf.pd.patterns.PDTilingPattern simplePDObject, PDResources inheritedResources) {
		super(simplePDObject, TILING_PATTERN_TYPE);
		this.inheritedResources = inheritedResources;
	}

	@Override
	public List<? extends Object> getLinkedObjects(String link) {

		if (CONTENT_STREAM.equals(link)) {
			return this.getContentStream();
		}
		return super.getLinkedObjects(link);
	}

	private List<PDContentStream> getContentStream() {
		List<PDContentStream> contentStreams = new ArrayList<>(MAX_NUMBER_OF_ELEMENTS);
		PDResourcesHandler handler = PDResourcesHandler.getInstance(
				this.inheritedResources, ((org.verapdf.pd.patterns.PDTilingPattern) this.simplePDObject).getResources());
		GFPDContentStream contentStream = new GFPDContentStream((org.verapdf.pd.PDContentStream) this.simplePDObject, handler);
		contentStreams.add(contentStream);
		return contentStreams;
	}
}