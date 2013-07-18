package org.bhawanisingh.projectxplorer.api.util;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Borders {
	public static Border PADDING_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	public static Border BEVEL_RAISED = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	public static Border BEVEL_LOWERED = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
	public static Border ETCHED_RAISED = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	public static Border ETCHED_LOWERED = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	public static Border COMPOUND_BEVEL_RAISED = BorderFactory.createCompoundBorder(Borders.BEVEL_RAISED, Borders.PADDING_BORDER);
	public static Border COMPOUND_BEVEL_LOWERED = BorderFactory.createCompoundBorder(Borders.BEVEL_LOWERED, Borders.PADDING_BORDER);
	public static Border COMPOUND_ETCHED_RAISED = BorderFactory.createCompoundBorder(Borders.ETCHED_RAISED, Borders.PADDING_BORDER);
	public static Border COMPOUND_ETCHED_LOWERED = BorderFactory.createCompoundBorder(Borders.ETCHED_LOWERED, Borders.PADDING_BORDER);
}
