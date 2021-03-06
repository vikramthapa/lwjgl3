/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package org.lwjgl.opengl

import org.lwjgl.generator.*
import org.lwjgl.system.linux.*

val GLXContext = "GLXContext".opaque_p

val GLXFBConfig = "GLXFBConfig".opaque_p
val GLXFBConfig_p = GLXFBConfig.p

val GLXFBConfigSGIX = "GLXFBConfigSGIX".opaque_p
val GLXFBConfigSGIX_p = GLXFBConfigSGIX.p

val GLXWindow = "GLXWindow".opaque_p
val GLXDrawable = "GLXDrawable".opaque_p
val GLXPixmap = "GLXPixmap".opaque_p

val GLXContextID = typedef(XID, "GLXContextID")

val GLXPbuffer = "GLXPbuffer".opaque_p

fun configGLX() {
	struct(OPENGL_PACKAGE, "GLXStereoNotifyEventEXT", "glx", mutable = false) {
		int.member("type", "GenericEvent")
		unsigned_long.member("serial", "\\# of last request server processed")
		Bool.member("send_event", "{@code True} if generated by {@code SendEvent} request")
		Display_p.member("display", "display the event was read from")
		int.member("extension", "GLX major opcode, from {@code XQueryExtension}")
		int.member("evtype", "always {@code GLX_STEREO_NOTIFY_EXT}")
		GLXDrawable.member("window", "XID of the X window affected")
		Bool.member("stereo_tree", "{@code True} if tree contains stereo windows")
	}
}