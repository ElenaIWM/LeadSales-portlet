package com.iwmn.a1.lead.web.portlets.utils;

public class TransliterateUtil {
	public static String cyrillicToLatin (String text){
		char[] abcCyr = {'А','Б','В','Г','Д','Ѓ','Е','Ж','З','Ѕ','И','Ј','К','Л','Љ','М','Н','Њ','О','П','Р','С','Т','Ќ','У','Ф','Х','Ц','Ч','Џ','Ш',
                'а','б','в','г','д','ѓ','е','ж','з','ѕ','и','ј','к','л','љ','м','н','њ','о','п','р','с','т','ќ','у','ф','х','ц','ч','џ','ш',','};
        String[] abcLat = {"A","B","V","G","D","Gj","E","Zh","Z","Dz","I","J","K","L","Lj","M","N","Nj","O","P","R","S","T","Kj","U","F","H","C","Ch","Dj","Sh",
                "a","b","v","g","d","gj","e","zh","z","dz","i","j","k","l","lj","m","n","nj","o","p","r","s","t","kj","u","f","h","c","ch","dj","sh"," "};
		    
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < text.length(); i++) {
			boolean appendChar = true;
		    for(int x = 0; x < abcCyr.length; x++ )
		    if (text.charAt(i) == abcCyr[x]) {
		        builder.append(abcLat[x]);
		        appendChar = false;
		    }
		    if(appendChar) {
		    	builder.append(text.charAt(i));
		    }
		}
		return builder.toString();
	}

}
