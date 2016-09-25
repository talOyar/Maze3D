package io;

import java.io.IOException;
import java.io.OutputStream;
/**
 * <h2>MyCompressorOutputStream class<h2>
 * <p> This class extends outputStream and overrides the write(byte[] ) and write(int ) methods
 * <p> This class gets an array of bytes compress it and saves it to an output
 * 
 * @author Tal Oyar & Tomer Cohen
 * @since 2016-08-30
 * @version 1.0
 *
 *
 *@see outputStream
 *
 */

public class MyCompressorOutputStream extends OutputStream {
	OutputStream out;
	byte[] comprresedByte;
	
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}

	public void setComprresByte(byte[] Bytecomprresed){
		this.comprresedByte = Bytecomprresed;
	}
	/**
	 * <p> write(byte[] b) method
	 * <p> this method receives an array of bytes then compress it and saves it to the output 
	 * that was initialize in the contractor
	 */
	@Override
	public void write(byte[] b) throws IOException {
		int counter=1;
		byte cuurByte = b[0];
					
		for(int i=1;i<b.length;i++){
			
			if(cuurByte != b[i]){
				
			while(counter >= 255){
				
				out.write(255);
				out.write(cuurByte);
				counter -=255;
				}
			
			out.write(counter);
			out.write(cuurByte);
			counter=1;
			cuurByte = b[i];
			}
			else 
				counter++;
			}
		
		
		while(counter >= 255){
			
			out.write(255);
			out.write(cuurByte);
			counter -=255;
			}
		
		//write the last 
		out.write(counter);
		out.write(cuurByte);
		}

	@Override
	public void write(int b) throws IOException {
	
		out.write(b);
	}
	
}
