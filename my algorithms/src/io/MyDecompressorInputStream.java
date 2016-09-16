package io;

import java.io.IOException;
import java.io.InputStream;
/**
 * <h2>MyDecompressorInputStream class<h2>
 * <p> This class extends InputStream and overrides the read(byte[] ) and read(int ) methods
 * <p> This class gets an array of bytes then reads into it the decompressed array from the input 
 * 
 * @author Tal Oyar & Tomer Cohen
 * @since 2016-08-30
 * @version 1.0
 *
 *
 *@see InputStream
 *
 */
public class MyDecompressorInputStream extends InputStream {
	InputStream in;
	
	
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public int read() throws IOException {
		
		return in.read();
	}
	/**
	 * <p> read(byte[] b) method
	 * <p> this method receives an array of bytes then reads into it the decompressed array from the input 
	 * that was initialize in the contractor
	 */
	public int read(byte[] arr) throws IOException {
		
		byte b;
		int size=0;
		while (size<arr.length) {
			
			int count = in.read();
			b =	(byte)in.read();
				
				
			for (int j = 0; j < count; j++) {
				arr[size++] = b;
			}
			
		}
		
		
		return arr.length;	
		}

	}
		