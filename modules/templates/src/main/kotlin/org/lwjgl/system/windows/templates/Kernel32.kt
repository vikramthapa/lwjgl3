/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.system.windows.templates

import org.lwjgl.generator.*
import org.lwjgl.system.windows.*

val Kernel32 = "Kernel32".nativeClass(WINDOWS_PACKAGE, binding = simpleBinding("kernel32", callingConvention = CallingConvention.STDCALL)) {
	documentation = "Native bindings to kernel32.dll."

	IntConstant(
		"Maximum number of wait objects",

		"MAXIMUM_WAIT_OBJECTS"..64 // winnt.h
	)

	IntConstant(
		"Signal timeout values.",

		"IGNORE"..0,
		"INFINITE"..0xFFFFFFFF.i
	)

	BOOL(
		"CloseHandle",
	    "Closes an open object handle.",

	    HANDLE.IN("hObject", "a valid handle to an open object")
	)

	NativeName("CreateEventW")..HANDLE(
		"CreateEvent",
		"""
		<a href="https://msdn.microsoft.com/en-us/library/windows/desktop/ms682396.aspx">MSDN reference</a>

		Creates or opens a named or unnamed event object.
		""",

		nullable../*LPSECURITY_ATTRIBUTES*/voidptr.IN(
			"lpEventAttributes",
			"""
			a pointer to a SECURITY_ATTRIBUTES structure. If this parameter is $NULL, the handle cannot be inherited by child processes. The
			{@code lpSecurityDescriptor} member of the structure specifies a security descriptor for the new event. If {@code lpEventAttributes} is $NULL, the
			event gets a default security descriptor. The ACLs in the default security descriptor for an event come from the primary or impersonation token of
			the creator.
			"""
		),
		BOOL.IN(
			"bManualReset",
			"""
			if this parameter is #TRUE, the function creates a manual-reset event object, which requires the use of the #ResetEvent() function to set the event
			state to nonsignaled. If this parameter is #FALSE, the function creates an auto-reset event object, and system automatically resets the event state
			to nonsignaled after a single waiting thread has been released.
			"""
		),
		BOOL.IN("bInitialState", "if this parameter is #TRUE, the initial state of the event object is signaled; otherwise, it is nonsignaled."),
		nullable..LPCTSTR.IN(
			"lpName",
			"""
			the name of the event object. The name is limited to #MAX_PATH characters. Name comparison is case sensitive.

			If {@code lpName} matches the name of an existing named event object, this function requests the #EVENT_ALL_ACCESS access right. In this case, the
			{@code bManualReset} and {@code bInitialState} parameters are ignored because they have already been set by the creating process. If the
			{@code lpEventAttributes} parameter is not $NULL, it determines whether the handle can be inherited, but its security-descriptor member is ignored.

			If {@code lpName} is $NULL, the event object is created without a name.

			If {@code lpName} matches the name of another kind of object in the same namespace (such as an existing semaphore, mutex, waitable timer, job, or
			file-mapping object), the function fails and the #GetLastError() function returns #ERROR_INVALID_HANDLE. This occurs because these objects share
			the same namespace.

			The name can have a "Global\" or "Local\" prefix to explicitly create the object in the global or session namespace. The remainder of the name can
			contain any character except the backslash character (\). Fast user switching is implemented using Terminal Services sessions. Kernel object names
			must follow the guidelines outlined for Terminal Services so that applications can support multiple users.

			The object can be created in a private namespace.
			"""
		)
	)

	BOOL(
		"ResetEvent",
		"Sets the specified event object to the nonsignaled state.",

		HANDLE.IN("hEvent", "a handle to the event object")
	)

	DWORD(
		"WaitForSingleObject",
		"Waits until the specified object is in the signaled state or the time-out interval elapses.",

		HANDLE.IN(
			"hHandle",
			"""
			a handle to the object. If this handle is closed while the wait is still pending, the function's behavior is undefined. The handle must have the
			#SYNCHRONIZE access right.
			"""
		),
		DWORD.IN(
			"dwMilliseconds",
			"""
			the time-out interval, in milliseconds. If a nonzero value is specified, the function waits until the object is signaled or the interval elapses.
			If {@code dwMilliseconds} is zero, the function does not enter a wait state if the object is not signaled; it always returns immediately. If
			{@code dwMilliseconds} is #INFINITE, the function will return only when the object is signaled.

			The {@code dwMilliseconds} value does not include time spent in low-power states. For example, the timeout will not keep counting down while the
			computer is asleep.
			"""
		)
	)

	DWORD(
		"WaitForMultipleObjects",
		"""
		<a href="https://msdn.microsoft.com/en-us/library/windows/desktop/ms687025.aspx">MSDN reference</a>

		Waits until one or all of the specified objects are in the signaled state or the time-out interval elapses.
		""",

		AutoSize("lpHandles")..DWORD.IN(
			"nCount",
			"""
			the number of object handles in the array pointed to by {@code lpHandles}. The maximum number of object handles is #MAXIMUM_WAIT_OBJECTS. This
			parameter cannot be zero.
			"""
		),
		const..HANDLE_p.IN(
			"lpHandles",
			"""
			an array of object handles. The array can contain handles to objects of different types. It may not contain multiple copies of the same handle.
			If one of these handles is closed while the wait is still pending, the function's behavior is undefined. The handles must have the
			<b>SYNCHRONIZE</b> access right.
			"""
		),
		BOOL.IN(
			"bWaitAll",
			"""
			if this parameter is #TRUE, the function returns when the state of all objects in the {@code lpHandles} array is signaled. If #FALSE, the function
			returns when the state of any one of the objects is set to signaled. In the latter case, the return value indicates the object whose state caused
			the function to return.
			"""
		),
		DWORD.IN(
			"dwMilliseconds",
			"""
			the time-out interval, in milliseconds. If a nonzero value is specified, the function waits until the specified objects are signaled or the
			interval elapses. If {@code dwMilliseconds} is zero, the function does not enter a wait state if the specified objects are not signaled; it always
			returns immediately. If {@code dwMilliseconds} is #INFINITE, the function will return only when the specified objects are signaled.
			"""
		)
	)
}