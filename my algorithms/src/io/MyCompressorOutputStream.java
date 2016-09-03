package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	
	byte[] b=null;

	OutputStream out;
	byte[] comprresedByte;
	public MyCompressorOutputStream(OutputStream out, byte[] b) {
		super();
		this.out = out;
		this.b=b;
	}
	

	
	@Override
	public void write(byte[] b) throws IOException {
		int counter=0;
		for(int i=0;i<b.length;i++){
			if(i<=9)
			{
				write(b[i]);
			}	
			else{
				
			if(b[i]==b[i-1]){
				counter++;
			}
			else{
				write(counter);
				write(b[i]);
			}
		}
		}	
	}

	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}


}
