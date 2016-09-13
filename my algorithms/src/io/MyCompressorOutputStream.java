package io;

import java.io.IOException;
import java.io.OutputStream;


public class MyCompressorOutputStream extends OutputStream {
	OutputStream out;
	byte[] comprresedByte;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
		
	}
	public void setComprresByte(byte[] Bytecomprresed){
		this.comprresedByte = Bytecomprresed;
	}
	
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
		
		out.write(counter);
		out.write(cuurByte);
		}
	
	
	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}


}
