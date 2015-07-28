/*
 * AndroidPBC.java
 * Copyright (C) 2015 Sietse Ringers
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 * USA
 */

package net.sietseringers.androidpbc;

/**
 * Container of the static Initialize() method that loads the static libraries.
 */
public class AndroidPBC {

	/**
	 * Load the native libraries (their .so files). This must be called before
	 * they are used.
	 *
	 * @throws UnsatisfiedLinkError if linking to one or more of the native
	 * libraries failed.
	 */
	public static void Initialize() throws UnsatisfiedLinkError {
		System.loadLibrary("gmp");
		System.loadLibrary("jnidispatch");
		System.loadLibrary("jpbc-pbc");
	}
}
