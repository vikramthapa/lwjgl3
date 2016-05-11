/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.odbc

import org.lwjgl.generator.*
import org.lwjgl.system.windows.*

val ODBC_PACKAGE = "org.lwjgl.odbc"

val ODBC_BINDING = simpleBinding("odbc", "\"odbc32\"")
val ODBC_BINDING_DELEGATE = ODBC_BINDING.delegate("SQL.getLibrary()")

val SQLCHAR = typedef(unsigned_char, "SQLCHAR")
val SQLSCHAR = IntegerType("SQLSCHAR", PrimitiveMapping.BYTE, unsigned = true)
val SQLWCHAR = typedef(unsigned_short, "SQLWCHAR")

val SQLTCHAR = typedef(SQLWCHAR, "SQLTCHAR")

val SQLCHARASCII_p = CharSequenceType(name = "SQLCHAR", charMapping = CharMapping.ASCII)
val SQLCHARUTF16_p = CharSequenceType(name = "SQLWCHAR", charMapping = CharMapping.UTF16)

val SQLSMALLINT = typedef(short, "SQLSMALLINT")
val SQLSMALLINT_p = SQLSMALLINT.p
val SQLUSMALLINT = typedef(unsigned_short, "SQLUSMALLINT")
val SQLUSMALLINT_p = SQLUSMALLINT.p

val long = IntegerType("long", PrimitiveMapping.INT)
val unsigned_long = IntegerType("unsigned long", PrimitiveMapping.INT, unsigned = true)

val SQLINTEGER = typedef(long, "SQLINTEGER")
val SQLINTEGER_p = SQLINTEGER.p
val SQLUINTEGER = typedef(unsigned_long, "SQLUINTEGER")

val SQLRETURN = typedef(SQLSMALLINT, "SQLRETURN")

val SQLPOINTER = typedef(void_p, "SQLPOINTER")
val SQLPOINTER_p = SQLPOINTER.p

val SQLLEN = IntegerType("SQLLEN", PrimitiveMapping.POINTER)
val SQLLEN_p = SQLLEN.p
val SQLULEN = IntegerType("SQLULEN", PrimitiveMapping.POINTER, unsigned = true)
val SQLULEN_p = SQLULEN.p

val SQLSETPOSIROW = IntegerType("SQLSETPOSIROW", PrimitiveMapping.LONG, unsigned = true)

val SQLHANDLE = PointerType("SQLHANDLE", includesPointer = true)
val SQLHANDLE_p = SQLHANDLE.p

val SQLHENV = typedef(SQLHANDLE, "SQLHENV")
val SQLHDBC = typedef(SQLHANDLE, "SQLHDBC")
val SQLHDBC_p = SQLHDBC.p
val SQLHSTMT = typedef(SQLHANDLE, "SQLHSTMT")
val SQLHDESC = typedef(SQLHANDLE, "SQLHDESC")
val SQLHWND = typedef(HWND, "SQLHWND")

val RETCODE = IntegerType("RETCODE", PrimitiveMapping.SHORT)
val RETCODE_p = RETCODE.p

// sqltypes.h structs

val SQL_DATE_STRUCT = struct(ODBC_PACKAGE, "SQL_DATE_STRUCT") {
	SQLSMALLINT.member("year", "")
	SQLUSMALLINT.member("month", "")
	SQLUSMALLINT.member("day", "")
}.nativeType

val SQL_TIME_STRUCT = struct(ODBC_PACKAGE, "SQL_TIME_STRUCT") {
	SQLUSMALLINT.member("hour", "")
	SQLUSMALLINT.member("minute", "")
	SQLUSMALLINT.member("second", "")
}.nativeType

val SQL_TIMESTAMP_STRUCT = struct(ODBC_PACKAGE, "SQL_TIMESTAMP_STRUCT") {
	SQLSMALLINT.member("year", "")
	SQLUSMALLINT.member("month", "")
	SQLUSMALLINT.member("day", "")
	SQLUSMALLINT.member("hour", "")
	SQLUSMALLINT.member("minute", "")
	SQLUSMALLINT.member("second", "")
	SQLUINTEGER.member("fraction", "")
}.nativeType

val SQL_YEAR_MONTH_STRUCT = struct(ODBC_PACKAGE, "SQL_YEAR_MONTH_STRUCT") {
	SQLUINTEGER.member("year", "")
	SQLUINTEGER.member("month", "")
}.nativeType

val SQL_DAY_SECOND_STRUCT = struct(ODBC_PACKAGE, "SQL_DAY_SECOND_STRUCT") {
	SQLUINTEGER.member("day", "")
	SQLUINTEGER.member("hour", "")
	SQLUINTEGER.member("minute", "")
	SQLUINTEGER.member("second", "")
	SQLUINTEGER.member("fraction", "")
}.nativeType

val SQLINTERVAL = typedef(int, "SQLINTERVAL")
val SQL_INTERVAL_STRUCT = struct(ODBC_PACKAGE, "SQL_INTERVAL_STRUCT") {
	SQLINTERVAL.member("interval_type", "")
	SQLSMALLINT.member("interval_sign", "")
	struct("intval", "") {
		SQL_YEAR_MONTH_STRUCT.member("year_month", "")
		SQL_DAY_SECOND_STRUCT.member("day_second", "")
	}
}.nativeType

val SQL_NUMERIC_STRUCT = struct(ODBC_PACKAGE, "SQL_NUMERIC_STRUCT") {
	SQLCHAR.member("precision", "")
	SQLSCHAR.member("scale", "")
	SQLCHAR.member("sign", "")
	SQLCHAR.array("val", "", size = 16 /*SQL_MAX_NUMERIC_LEN*/)
}.nativeType

// sqlncli.h structs

val DBMONEY = struct(ODBC_PACKAGE, "DBMONEY") {
	LONG.member("mnyhigh", "")
	ULONG.member("mnylow", "")
}.nativeType

val DBDATETIME = struct(ODBC_PACKAGE, "DBDATETIME") {
	LONG.member("dtdays", "")
	ULONG.member("dttime", "")
}.nativeType

val DBDATETIM4 = struct(ODBC_PACKAGE, "DBDATETIM4") {
	USHORT.member("numdays", "")
	USHORT.member("nummins", "")
}.nativeType

val SQL_SS_TIME2_STRUCT = struct(ODBC_PACKAGE, "SQL_SS_TIME2_STRUCT") {
	SQLUSMALLINT.member("hour", "")
	SQLUSMALLINT.member("minute", "")
	SQLUSMALLINT.member("second", "")
	SQLUSMALLINT.member("fraction", "")
}.nativeType

val SQL_SS_TIMESTAMPOFFSET_STRUCT = struct(ODBC_PACKAGE, "SQL_SS_TIMESTAMPOFFSET_STRUCT") {
	SQLSMALLINT.member("year", "")
	SQLUSMALLINT.member("month", "")
	SQLUSMALLINT.member("day", "")
	SQLUSMALLINT.member("hour", "")
	SQLUSMALLINT.member("minute", "")
	SQLUSMALLINT.member("second", "")
	SQLUINTEGER.member("fraction", "")
	SQLSMALLINT.member("timezone_hour", "")
	SQLSMALLINT.member("timezone_minute", "")
}.nativeType

val SQLPERF = struct(ODBC_PACKAGE, "SQLPERF") {
	// Application Profile Statistics
	DWORD.member("TimerResolution", "")
	DWORD.member("SQLidu", "")
	DWORD.member("SQLiduRows", "")
	DWORD.member("SQLSelects", "")
	DWORD.member("SQLSelectRows", "")
	DWORD.member("Transactions", "")
	DWORD.member("SQLPrepares", "")
	DWORD.member("ExecDirects", "")
	DWORD.member("SQLExecutes", "")
	DWORD.member("CursorOpens", "")
	DWORD.member("CursorSize", "")
	DWORD.member("CursorUsed", "")
	LDOUBLE.member("PercentCursorUsed", "")
	LDOUBLE.member("AvgFetchTime", "")
	LDOUBLE.member("AvgCursorSize", "")
	LDOUBLE.member("AvgCursorUsed", "")
	DWORD.member("SQLFetchTime", "")
	DWORD.member("SQLFetchCount", "")
	DWORD.member("CurrentStmtCount", "")
	DWORD.member("MaxOpenStmt", "")
	DWORD.member("SumOpenStmt", "")
	// Connection Statistics
	DWORD.member("CurrentConnectionCount", "")
	DWORD.member("MaxConnectionsOpened", "")
	DWORD.member("SumConnectionsOpened", "")
	DWORD.member("SumConnectiontime", "")
	LDOUBLE.member("AvgTimeOpened", "")
	// Network Statistics
	DWORD.member("ServerRndTrips", "")
	DWORD.member("BuffersSent", "")
	DWORD.member("BuffersRec", "")
	DWORD.member("BytesSent", "")
	DWORD.member("BytesRec", "")
	// Time Statistics
	DWORD.member("msExecutionTime", "")
	DWORD.member("msNetWorkServerTime", "")
}.nativeType