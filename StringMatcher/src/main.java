import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
	 static String resstring[] = {"","","",""};
		public static List<Integer> match(String pattern, String text) {
	 		List<Integer> matches = new ArrayList<Integer>();
	 		int m = text.length();
	 		int n = pattern.length();
	 
	 		Map<Character, Integer> rightMostIndexes = preprocessForBadCharacterShift(pattern);
	 	
	 		int alignedAt = 0;
	 		while (alignedAt + (n - 1) < m) {
	 
	 			for (int indexInPattern = n - 1; indexInPattern >= 0; indexInPattern--) {
	 				int indexInText = alignedAt + indexInPattern;
	 				char x = text.charAt(indexInText);
	 				char y = pattern.charAt(indexInPattern);
	 
	 				if (indexInText >= m)
	 					break;
	 
	 				if (x != y) {
	 
	 					Integer r = rightMostIndexes.get(x);
	 
	 					if (r == null) {
	 						alignedAt = indexInText + 1;
	 					}
	 
	 					else {
	 						int shift = indexInText - (alignedAt + r);
	 						alignedAt += shift > 0 ? shift : 1;
	 					}
	 
	 					break;
	 				}
	 				else if (indexInPattern == 0) {
	 					matches.add(alignedAt);
	 					alignedAt++;
	 				}
	 
	 			}
	 		}
	 		return matches;
	 	}
		
	 	private static Map<Character, Integer> preprocessForBadCharacterShift(
	 			String pattern) {
	 		Map<Character, Integer> map = new HashMap<Character, Integer>();
	 		for (int i = pattern.length() - 1; i >= 0; i--) {
	 			char c = pattern.charAt(i);
	 			if (!map.containsKey(c)) map.put(c, i);
	 		}
	 
	 		return map;
	 	}
	 	public static String numtra(String arrayin)//目前用"兩"這個字會出現問題
	 	{
	 		
	 		//2012-04-04新增星期幾判斷式的前置作業
	 		/*int weekdayflag=arrayin.indexOf("日");
	 		boolean weekday=false;
	 		if (weekdayflag>0){weekday=true;}
	 		while(weekday)
	 		{
	 		arrayin=arrayin.replace("日","天");
	 		weekdayflag=arrayin.indexOf("日");
	 		if (weekdayflag>0){weekday=true;}else {weekday=false;}
	 		}*/
	 		
	 		
	 		
	    	String[] month={"January","February","March","April","May","June","July","August","September","October","November","December","january","february","march","april","may","june","july","august","september","october","november","december","JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER","jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	    	int monthnum=0;
	    	for(monthnum=0;monthnum<month.length;monthnum++)
	    	{
	    		arrayin=arrayin.replace(month[monthnum],String.valueOf((monthnum%12)+1));
	    	}
	    	String[] half={"half","HALF","Half","半"};
	    	int halfnum=0;
	    	for(halfnum=0;halfnum<half.length;halfnum++)
	    	{
	    		arrayin=arrayin.replace(half[halfnum],"30");
	    	}
	 		
	    	arrayin="smartcalendarstart"+arrayin+"smartcalendarend";
	 		
	 		char[] arrayinarray=arrayin.toCharArray();
	 		int i,j,k,l,x,y;
	 		String str = "一二三四五六七八九";
	 		//String str = "123456789";
	 		char[] strarray=str.toCharArray();
	 		String space="";
	 		char[] spacearray=space.toCharArray();
	 		char two='兩';
	 		String temp ="";
	    	int ten11[] = new int[arrayinarray.length ];
	    	int ten00[] = new int[arrayinarray.length ];

	 		for(i=0;i<arrayin.length();i++)//將國字轉成數字開始
	 		{
	 			for(j=0;j<9;j++)
	 			{
	 				if(arrayinarray[i]==strarray[j])
	 				{
	 					temp=j+1+"";
	 					char[] temparray=temp.toCharArray();
	 					arrayinarray[i]=temparray[0];
	 					//System.out.println("數字轉換："+(j+1)+","+temparray[0]);
	 				}
	 			}
	 			if(arrayinarray[i]==two)//2011-11-09修正"兩"這個字判斷使用
	 			{
	 				
						
						arrayinarray[i]='2';
	 			}
	 		}//將國字轉成數字結束
	 		for(i=0;i<arrayinarray.length;i++)////將國字十轉成數字開始
	 		{
	 			//System.out.println("test num 0");
	 			//System.out.println(arrayinarray[i]);
	 			k=i;
					//2011-10-11 將原本的k=i-1;改成k=i;
	 			l=i+1;
	 			//if(arrayinarray[i]=='*')
	 			if(arrayinarray[i]=='十')
	 			{					
	 				//System.out.println(k+"+"+l+"+"+arrayinarray.length);

	 				if((k>=0)&&(l<=arrayinarray.length))
	 				{
	 					//System.out.println("test num 1");

	 					if(Character.isDigit(arrayinarray[k-1])&&Character.isDigit(arrayinarray[l]))//2011/12/7因為哈摟 還記得我嗎 我們同學會訂在11月的32號晚間的六點三十地點是我們當初打老師的那個B餐廳要記得到唷~此範例關係 以下k變成k-1開始
	 					{
	 						//System.out.println("test num 2");
	 						//arrayinarray[i]=spacearray[0];
	 						ten11[i]=1;
	 					}
	 					else if(!Character.isDigit(arrayinarray[k-1])&&Character.isDigit(arrayinarray[l]))
	 					{
	 						//System.out.println("test num 3");
	 						arrayinarray[i]='1';
	 					}
	 					else if(Character.isDigit(arrayinarray[k-1])&&!Character.isDigit(arrayinarray[l]))
	 					{
	 						//System.out.println("test num 4");
	 						arrayinarray[i]='0';
	 					}
	 					else if(!Character.isDigit(arrayinarray[k-1])&&!Character.isDigit(arrayinarray[l]))//2011/12/7因為哈摟 還記得我嗎 我們同學會訂在11月的32號晚間的六點三十地點是我們當初打老師的那個B餐廳要記得到唷~此範例關係 以上k變成k-1結束
	 					{

	 						//System.out.println("test num 5");
	 						/*for(x=arrayinarray.length;x>i;x--)
	 						{
	 							arrayinarray[x]=arrayinarray[x-1];
	 						}
	 						arrayinarray[i+1]='0';
	 						arrayinarray[i]='1';
	 						ten00[i]=1;*/



	 						
	 					}
	 				}
	 				
	 			}
	 		}//將國字轉成數字結束
	 		String arrayout="";
	 		//System.out.print("\"\"");
	 		for(i=0;i<arrayinarray.length;i++)
	 		{
	 			if(ten11[i]==1)
	 			{
	 				arrayout+="";
	 			}
	 			else if(ten00[i]==1)
	 			{
	 				arrayout+="10";
	 			}
	 			else
	 			{
	 			arrayout+=arrayinarray[i];
	 			}
	 			//System.out.print(arrayinarray[i]);
	 		}
	 		//System.out.println("\"\"");
	 		while(arrayout.indexOf("十")>0)
	 		{
	 			
	 			arrayout=arrayout.replace("十", "10");
	 		}
	 		

	 	return arrayout;	
	 	}
	 	public static String dataint(String arrayin,String keyin,int slength,int matcherflag)
	    {

	 		if(matcherflag==5)
	 		{
	 			arrayin=arrayin.replace("smartcalendarend","");
	 		}
	    	char[] array = arrayin.toCharArray();
	    	char[] keyarray=keyin.toCharArray();
	    	String arrayout;
	    	arrayout="";

	    	
	    	//System.out.print("testall\n");
	     	int countdata=0;
	     	int countdatacheck=0;
	     	int count=0;
	     	int flag=0;
	     	int prt=0;
	     	int keynum=0;//判斷進入的第一或第二個字是否是數字
	     	String matcher="";
	     if(matcherflag==5)//2011/10/27開始處理地點
	     {
	    	 //System.out.println("testadd"+arrayin);
	    	 int len = 0;
	    	 int addi,addj,max;
	    	 prt=3;
	    	 
	    	 for(addi=0;addi<arrayin.length();addi++)
	    	 {
	    		 if(array[addi]==keyarray[0])
	    		 {
	    			 len=addi;
	    			 break;
	    		 }
	    	 }
	    	 if(len+6<arrayin.length())//2011-12-26改成取關建字後六個字
	    	 {
	    		 for(addj=len;addj<len+6;addj++)
	    		 {
	    			 arrayout+=array[addj];
	    		 }
	    	 }
	    	 else
	    	 {
	    		 max=arrayin.length()-len;
	    		 for(addj=len;addj<len+max;addj++)
	    		 {
	    			 arrayout+=array[addj];
	    		 }
	    	 }
	    	 

	    	 return arrayout;
	     }//處理地點結束
	     	
	     if(!Character.isDigit(array[0]))
	     {
	    	 keynum=1;
	     }

	     //System.out.println("第幾個字是數字"+keynum+"+關鍵字="+keyin);
	     int keycount=0;
	     	
	 	   for(int index=0+keynum; index < slength; index++) {  
	 	     if(!Character.isDigit(array[index])) {
	 	    	 if(array[index]==keyarray[0]&&keycount==0)
	 	    	 {
	 	    		 countdata++;
	 	    		 keycount++;
	 	    		 count=index;
	 	    		
	 	    		 
	 	    	 }
	 	    	 else
	 	    	 {
	 	    		 
	 	    		
	 	    		 if(count+1<=index)	//這個if是在判斷關鍵字的後面如果不是數字的話結果將不印出
	 	    		 {
	 	    			 if((array[index]==array[count+1]&&matcherflag==4))
	 	    			 {
	 	    				 prt=2;
	 	    			 }
	 	    			 else if(array[index]==array[count+1]&&(matcherflag!=2))
	 	    			 {
	 	    				 prt=1;
	 	    			 }
	 	    		 }					//這個if是在判斷關鍵字的後面如果不是數字的話結果將不印出
	 	    		
	         	     break; //字串中出現一個非數值的字原就結束 loop, 因為這不是我們要的字串
	 	    		 
	 	    	 }
	 	     
	 	     }
	 	     else
	 	     {
		    		 countdata++;
	 	    	 
	 	     }
	 	     
	 	     
	 	   }
	 	  //System.out.println("test1+"+keynum+"+"+countdata);
	 	   //System.out.println("test1+"+prt);
	 	   
	   	for(int i=0+keynum;i<countdata;i++)
		{
	   		//System.out.println("test0");
	   		//System.out.println("array[i]="+array[i]+"+keyarray[0]="+keyarray[0]);
	   		
				//System.out.print(array[i]);
	   		if(array[0]==keyarray[0])
	   		{
	   			//System.out.println("test1-1");
	   			prt=1;
	   			break;
	   		}
	   		else if(array[i]==keyarray[0])//這個elseif的部分則是判斷關鍵字後面第三個字如果是數字的話將去除
	   		{
	   			//System.out.println(i+"+"+array[i]+"+"+keyarray[0]+"+"+arrayin);
				//System.out.println(count+"+"+countdata);
	   			count=i+3;
	   			if(count<=countdata)//判斷關鍵字後面兩個字
	   			{
	   				//System.out.println("test1-2+"+arrayin+"+"+count);

	   				/*if(arrayin.length()==count)//2011-09-08 時間或日期出現在尾端時發生錯誤的處理開始;2011-09-27新方法測試開始
	   				{
	   					countdatacheck=count;
	   					break;
	   				}
	   				else if(arrayin.length()<count)
	   				{
	   					countdatacheck=countdata;
	   					break;
	   					
	   				}//2011-09-08 時間或日期出現在尾端時發生錯誤的處理結束;2011-09-27新方法測試結束*/
	   		
	   				if(Character.isDigit(array[count]))
	   				{
	   					//System.out.println("test1-2-1");
	   					countdatacheck=count;   					
	   				}
	   				else
	   				{
	   					//System.out.println("test1-2-2");
	   					countdatacheck=countdata;

	   				}
	   			}
	   			else if(count<=countdata+1)//判斷關鍵字後面一個字
	   			{
	   				//System.out.println("test1-3"+arrayin);
	   				//System.out.println("test1-3");
	   				//System.out.println(count+"+"+arrayin.length());
	   				count=count-1;
	   				if(count>=arrayin.length())//2011-09-08 時間或日期出現在尾端時發生錯誤的處理開始
	   				{
	   					countdatacheck=arrayin.length();
	   					break;
	   				}//2011-09-08 時間或日期出現在尾端時發生錯誤的處理結束
	   				if(Character.isDigit(array[count]))
	   				{
	   					countdatacheck=count;   					
	   				}
	   				else
	   				{
	   					countdatacheck=countdata;

	   				}
	   				
	   			}
	   			else//2011-09-08 時間或日期出現只有三個字的時候發生錯誤的處理開始 ex:1:20;2011-09-27新方法測試開始
	   			{
	   				//System.out.println("test1-4");
	   				
	   				//2011-09-08防止格式錯誤
	   				if(Character.isDigit(array[2]))
	   				{
	   					
	   				}
	   				else
	   				{
	   					countdatacheck=count-2;
	   				}
	   				
	   				
	   				
	   			}//2011-09-08 時間或日期出現只有三個字的時候發生錯誤的處理結束 ex:1:20;2011-09-27新方法測試結束//2011-10-06 這段還是有存在的必須性，避免三個字的被去掉
	   			//System.out.println("test1-5");
	   			
	   		}							//這個elseif的部分則是判斷關鍵字後面第三個字如果是數字的話將去除
	   		else if(matcherflag==2)//2011-09-14只有日期的時候開始
	   		{
	   			
	   			/*count=i+1;
	   			countdatacheck=count;*/
	   			if(keynum==0)
	   			{
	   				countdatacheck=3;
	   			}
	   			else if(keynum==1)
	   			{
	   				countdatacheck=2;
	   			}

	   			//System.out.println("test1-6+"+countdatacheck);
	   		}//2011-09-14只有日期的時候結束
	   		else if(matcherflag==4&&prt==2)//2011-09-21做點這個字的處理開始
	   		{
	   			
	   			prt=0;
	   			if(keynum==0)
	   			{
	   				countdatacheck=3;
	   			}
	   			else if(keynum==1)
	   			{
	   				countdatacheck=2;
	   			}

	   			//System.out.println("test1-7+"+arrayin.length());
	   			
	   		}//2011-09-21做點這個字的處理結束
	   		
	   	

	   	
		}
	   	//System.out.println("test2");
	   	//System.out.print(prt);
	   	
		/*try {
	        //取得SD卡路徑
	           File SDCardpath = Environment.getExternalStorageDirectory();
	           //File myDataPath = new File( SDCardpath.getAbsolutePath() + "/myData" );
	           //if( !myDataPath.exists() ) myDataPath.mkdirs();
	           //將資料寫入到SD卡
	           //FileWriter myFile = new FileWriter("/sdcard/myData/test.txt" );
	           FileWriter myFile = new FileWriter( SDCardpath.getAbsolutePath() + "/myData/test2.txt.txt" );
	           myFile.write(matcher);
	           myFile.close();
	           
	} catch (IOException e) {
	e.printStackTrace();
	}*/
		//System.out.println("test3");

	   	if(prt==0)
	   	{
				
	   		for(int i=0+keynum;i<countdatacheck+keynum;i++)
	   		{
	   			arrayout+=array[i];
	   			//System.out.print(array[i]);
	   		}
	   		
	   	}
	   	
	   	
	    if(array[0]=='0'&&!Character.isDigit(array[1]))//2011-12-7因哈摟 還記得我嗎 我們同學會訂在11月的32號晚間的六點三十地點是我們當初打老師的那個B餐廳要記得到唷~所以新增"0地點"這項判斷原則
	    {
	    	return "";
	    }
	   	
	   		
	   
	   	

	   	
		 
			return arrayout;
			
	    }

	 	public static String timepm(String arrayin,String keyin,int slength)
	    {
	 		int count=0;
	 		int tempnum;
	 		int dotflag=0;
	 		int tmp00=0;//用來判斷只給時沒給分的
	 		String returnstr="";
	 		String[] names;
	 		
	 		String splitString = arrayin;
	 		if(keyin==".")
	 		{
	 			names = splitString.split("\\.");
	 			dotflag=1;
	 		}
	 		else
	 		{
	 			names = splitString.split(keyin);
	 		}
	 		//System.out.println("timepm+"+names.length);
	 		for(String name:names){
	 			//System.out.println("pmname"+name);
	 			if(count==0)
	 			{
	 				tempnum=Integer.parseInt(name);
	 				if(tempnum+12<25)
	 				{
	 				tempnum+=12;
	 				}
	 				else if(tempnum+12>24)
	 				{
	 					return "";
	 				}
	 				returnstr+=Integer.toString(tempnum);
	 				if(dotflag==1)
	 				{
	 					returnstr+=":";
	 				}
	 				else
	 				{
	 					returnstr+=keyin;
	 				}
	 				count++;
	 				//System.out.println("timeout="+count);
	 			}
	 			else if(count!=0)
	 			{
	 				returnstr+=name;
	 				//System.out.println("pn+name"+name);
	 				tmp00++;
	 			}
	 			
	 		    //System.out.println(name);
	 		}
	 		
	 		if(tmp00==0)
	 		{
	 			//System.out.println("tmp00");
	 			returnstr+="00";
	 		}
	 		
	 		
	 		
	 		
			return returnstr+",";
	 		
	 		
	    }
	 	
	 	public static String format(String in,String key,int matcherflag)
	 	{
	 		//System.out.println("maat+"+matcherflag);

	 		String head = "";
	 		String keymat[]={"/","月","號","日","M","m","-",".","點",":","在","於"};
	 		String mat[]={"日期：","時間：","地點："};
	 		int temp = 100;
	 		int i,j;
	 		
	 		if(in=="")
	 		{
	 			return head;
	 		}
	 		
	 		
	 		/*for(i=0;i<12;i++)
	 		{
	 			if(key==keymat[i])
	 			{
	 				temp=i;
	 				break;
	 			}
	 		}
	 		*/
	 		if(matcherflag==1||matcherflag==2)
	 		{
	 			head+=mat[0];
	 			head+=in;
	 		}
	 		else if(key==".")
	 		{
	 			head+=mat[0];//2011-12-21把"."這個關建字跟日期時間做上關係改為只跟日期有關係 方便之後的處理
	 			head+=in;
	 			/*head+="\t";
	 			head+=mat[1];
	 			head+=in;*/
	 		}
	 		else if(matcherflag==3||matcherflag==4)
	 		{
	 			head+=mat[1];
	 			head+=in;
	 		}
	 		else if(matcherflag==5)
	 		{
	 			
	 			head+=mat[2];
	 			head+=in;
	 			          
	 		}
	 		else
	 		{
	 			head="";
	 		}
	 		
	 		
			return head;
	 		
	 		
	 	}

	 	
	 	public static String allunified(String in,String key,int matcherflag){//2011/12/14統一格式使用
	 		in=in.replace("日期：","");
	 		in=in.replace("時間：","");
	 		in=in.replace("地點：","");
	 		int keynum;
	 		int i,j;
	 		String temp;
	 		String attemp="";
	 		char arraykey[]=in.toCharArray();
	 		//2011-12-21已經".做例外處理"
	 		/*if(key==".")
	 		{
	 			for(i=0;i<in.length()/2;i++)
	 			{
	 				attemp+=arraykey[i];
	 			}
	 	 		in=attemp;
	 		}
	 		*/
	 		if (in=="")
	 		{
	 			return in;
	 		}

	 		//System.out.print("月月月月月"+in);
	 		keynum=in.indexOf(key);
	 		
	 		/*Time t=new Time(); 
	        t.setToNow(); 
	        int year = t.year;
	        int month = t.month+1;
	        int date = t.monthDay;
	        int hour = t.hour;    // 0-23
	        int minute = t.minute;
	        int second = t.second;*/
	 		int year = Calendar.getInstance().get(Calendar.YEAR);
			int month = Calendar.getInstance().get(Calendar.MONTH)+1;
			int date = Calendar.getInstance().get(Calendar.DATE);
			int hour = Calendar.getInstance().get(Calendar.HOUR);
			int minute = Calendar.getInstance().get(Calendar.MINUTE);
			int second = Calendar.getInstance().get(Calendar.SECOND);
	        
	 		
	 		int flag=0;
	 		if(keynum==(in.length()-1))
	 				{
	 				flag=1;
	 				}
	 		
	 		if(matcherflag==1)
	 		{
	 			in=in.replace(key,"/");
	 			//System.out.print("月月月月月月月月月月月月月月");
	 			
	 		}
	 		 if(matcherflag==2)
	 		{
	 			in=in.replace(key,"");
	 			if(Integer.valueOf(in)<date)
	 			{
	 				
	 				if(month+1>12)
	 				{
	 					in=((month+1)-12)+"/"+in;		
	 					//System.out.println("in1="+in);
	 					
	 				}
	 			in=(month+1)+"/"+in;
	 			//System.out.println("in2="+in);
	 			//System.out.println("inmonth"+month);
	 			}
	 			else
	 			{
	 				in=month+"/"+in;
	 			}
	 		}
	 		 if(flag==0&&(matcherflag==4))
	 		{
	 			in=in.replace(key,":");
	 		}
	 		 if(flag==1&&(matcherflag==4))
	 		{
	 			in=in.replace(key,":");
	 			in=in+"00";
	 		}
	 		//2011-12-21已經".做例外處理"
	 		 /*if(key==".")
	 		{
	 			temp=in;
	 			temp=temp.replace(key,"/");
	 			in=in.replace(key,":");
	 			temp=temp+","+in;
	 			in=temp;
	 			
	 		}*/

	 		
			
	 		
	 		
	 		
	 		return in;
	 		
	 	}
	 	public static Date datetodate(String in) throws ParseException
	 	{
	 		//欲轉換的日期字串
	 		String dateString = in;
	 		//設定日期格式
	 		//SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
	 		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat();
	 		sdf.applyPattern("MM-dd");
	 		//進行轉換
	 		Date date = sdf.parse(dateString);
	 		//System.out.println(date);
			return date;

	 		
	 	}
	 	public Date timetodate(String in) throws ParseException
	 	{
	 		//欲轉換的日期字串
	 		String dateString = in;
	 		//設定日期格式
	 		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	 		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat();
	 		sdf.applyPattern("HH:mm");
	 		//進行轉換
	 		Date date = sdf.parse(dateString);
	 		//System.out.println(date);
			return date;
	 	}
	 	public static String week(String strin)
	 	{
	 		//System.out.println("weekin");
	 		Date Currentdatey = new Date();
		    SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		    String yearString = sdfy.format(Currentdatey);
		    Date Currentdatem = new Date();
		    SimpleDateFormat sdfm = new SimpleDateFormat("MM");
		    String monthString = sdfm.format(Currentdatem);
		    Date Currentdated = new Date();
		    SimpleDateFormat sdfd = new SimpleDateFormat("dd");
		    String dateString = sdfd.format(Currentdated);
	 		
			Date Currentdate = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		    String weekString = sdf.format(Currentdate);
		    
		    //System.out.println("dateweek"+weekString);
		    int nowweek=0;
		    int nowi;
		    
	 		
			String[] weekstr={"星期日","星期一","星期二","星期三","星期四","星期五","星期六",
					"禮拜日","禮拜一","禮拜二","禮拜三","禮拜四","禮拜五","禮拜六",
					"周日","周一","周二","周三","周四","周五","周六",
					"週日","週一","週二","週三","週四","週五","週六",
					"星期7","星期1","星期2","星期3","星期4","星期5","星期6",
					"禮拜7","禮拜1","禮拜2","禮拜3","禮拜4","禮拜5","禮拜6",
					"周7","周1","周2","周3","周4","周5","周6",
					"週7","週1","週2","週3","週4","週5","週6",
					"sunday","monday","tuesday","wednesday","thursday","friday","saturday",
					"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday",
					"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY",
					"sun","mon","tue","wed","thu","fri","sat",
					"Sun","Mon","Tue","Wed","Thu","Fri","Sat",
					"SUN","MON","TUE","WED","THU","FRI","SAT",
					"日曜日","月曜日","火曜日","水曜日","木曜日","金曜日","土曜日",
					"(日)","(月)","(火)","(水)","(木)","(金)","(土)",
					"にちようび","げつようび","かようび","すいようび","もくようび","きんようび","どようび"
					};
			
			for(nowi=0;nowi<weekstr.length ;nowi++)
			{
				if(weekstr[nowi].equals(weekString))
				{
					nowweek=(nowi%7);
				}
				
				
			}
			

			
			
			
			
			int i,j,week,tmp,count=0,max=1;
			String restr="";
	 		/*Time t=new Time(); 
	        t.setToNow(); 
	        int year = t.year;
	        int month = t.month+1;
	        int date = t.monthDay;
	        int hour = t.hour;    // 0-23
	        int minute = t.minute;
	        int second = t.second;*/
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int month = Calendar.getInstance().get(Calendar.MONTH)+1;
			int date = Calendar.getInstance().get(Calendar.DATE);
			int hour = Calendar.getInstance().get(Calendar.HOUR);
			int minute = Calendar.getInstance().get(Calendar.MINUTE);
			int second = Calendar.getInstance().get(Calendar.SECOND);

	 		Calendar c = Calendar.getInstance();
	 		/*c.set(Calendar.YEAR ,Integer.valueOf(yearkString));
	 		c.set(Calendar.MONTH ,Integer.valueOf(monthString));
	 		c.set(Calendar.DATE ,Integer.valueOf(dateString));*/
	 		c.set(Integer.valueOf(yearString),Integer.valueOf(monthString),Integer.valueOf(dateString));
			
			if(strin.indexOf("下")>0||strin.indexOf("next")>0)
			{
				max++;
			}
			for(i=0;i<weekstr.length;i++)
			{
				if(strin.indexOf(weekstr[i])>0)
				{
					 c = Calendar.getInstance();
				 		c.set(Calendar.YEAR ,year);
				 		c.set(Calendar.MONTH ,month);
				 		c.set(Calendar.DATE ,date);
					week=(i%7)+1;
					//System.out.println(weekstr[i]+"DATAADD"+c.get(Calendar.DAY_OF_WEEK)+","+c.get(Calendar.MONTH)+","+c.get(Calendar.DATE));
					while(true)
					{
						int newweek=0;
						//int newweek=(((c.get(Calendar.DAY_OF_WEEK )+8)-4)%7)+1;//大月+1天，小月剛好，2月特殊潤-2  沒潤-1
						//calendar最後一天固定31，星期都是從每個月的最後一天往前排所以會有偏差值存在newweek是在修正偏差值使用
						if((c.get(Calendar.MONTH)==2))//calendar不管哪個月最後一天都是31，判斷月份來作星期的改變
						{
							if((c.get(Calendar.YEAR)%400==0)||((c.get(Calendar.MONTH)==2)&&(c.get(Calendar.YEAR))%4==0&&(!(c.get(Calendar.YEAR)%100==0))))
							{
							newweek=(((c.get(Calendar.DAY_OF_WEEK )+8)-3)%7)+1;
							}
							else
							{
								newweek=(((c.get(Calendar.DAY_OF_WEEK )+8)-2)%7)+1;	
							}
						}
						else if(c.get(Calendar.MONTH)==4||c.get(Calendar.MONTH)==6||c.get(Calendar.MONTH)==9||c.get(Calendar.MONTH)==11)
						{
							newweek=(((c.get(Calendar.DAY_OF_WEEK )+8)-4)%7)+1;
						}
						else
						{
							newweek=(((c.get(Calendar.DAY_OF_WEEK )+8)-5)%7)+1;
						}//calendar不管哪個月最後一天都是31，判斷月份來作星期的改變
						
						
						
						if(week==newweek)
						{
							count++;
							restr+=c.get(Calendar.MONTH)+"/"+c.get(Calendar.DATE)+" and ";
							//System.out.println("得到的星期日期為"+restr);
						}
						else
						{
							c.add(Calendar.DATE,1);
							
							if((c.get(Calendar.MONTH)==2))//calendar不管哪個月最後一天都是31，判斷月份最後一天是否要再加一天開始
							{
								if((c.get(Calendar.YEAR)%400==0)||((c.get(Calendar.MONTH)==2)&&(c.get(Calendar.YEAR))%4==0&&(!(c.get(Calendar.YEAR)%100==0))))
								{
									if(c.get(Calendar.DATE )==30)
									{
										c.add(Calendar.DATE,2);
									}
								}
								else
								{
									
									if(c.get(Calendar.DATE )==29)
									{
										c.add(Calendar.DATE,3);
									}
								}
							}
							else if(c.get(Calendar.MONTH)==4||c.get(Calendar.MONTH)==6||c.get(Calendar.MONTH)==9||c.get(Calendar.MONTH)==11)
							{
								if(c.get(Calendar.DATE )==30)
								{
									c.add(Calendar.DATE,2);
								}
							}
							else
							{
								
							}//calendar不管哪個月最後一天都是31，判斷月份最後一天是否要再加一天結束
							
							//System.out.println("DATAADD"+c.get(Calendar.DAY_OF_WEEK ));
						
						}
						if(count==max)
						{
							count=0;
							break;
						}
					}
					strin=strin.replace(weekstr[i],weekstr[i]+"calendar"+restr);
				}
				
				
				
			}
			
			
			
			//c.set(2012,4,4);
			c.get(Calendar.DAY_OF_WEEK);
			//System.out.println(c.get(Calendar.DAY_OF_WEEK));
			
			
			return strin;
	 		
	 		
	 	}
	 	
	 	public static String day(String strin)
	 	{
	 		/*Time t=new Time(); 
	        t.setToNow(); 
	        int year = t.year;
	        int month = t.month+1;
	        int date = t.monthDay;
	        int hour = t.hour;    // 0-23
	        int minute = t.minute;
	        int second = t.second;*/
	 		int year = Calendar.getInstance().get(Calendar.YEAR);
			int month = Calendar.getInstance().get(Calendar.MONTH)+1;
			int date = Calendar.getInstance().get(Calendar.DATE);
			int hour = Calendar.getInstance().get(Calendar.HOUR);
			int minute = Calendar.getInstance().get(Calendar.MINUTE);
			int second = Calendar.getInstance().get(Calendar.SECOND);
	        
	 		Calendar c = Calendar.getInstance();
	 		
	 		c.set(Calendar.YEAR ,year);
	 		c.set(Calendar.MONTH ,month);
	 		c.set(Calendar.DATE ,date);
	 		//System.out.println("dayweek"+c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
	 		
	 		String tom,dat;
	 		c.add(Calendar.DATE,1);
	 		
			if((c.get(Calendar.MONTH)==2))//calendar不管哪個月最後一天都是31，判斷月份最後一天是否要再加一天開始
			{
				if((c.get(Calendar.YEAR)%400==0)||((c.get(Calendar.MONTH)==2)&&(c.get(Calendar.YEAR))%4==0&&(!(c.get(Calendar.YEAR)%100==0))))
				{
					if(c.get(Calendar.DATE )==30)
					{
						c.add(Calendar.DATE,2);
					}
				}
				else
				{
					
					if(c.get(Calendar.DATE )==29)
					{
						c.add(Calendar.DATE,3);
					}
				}
			}
			else if(c.get(Calendar.MONTH)==4||c.get(Calendar.MONTH)==6||c.get(Calendar.MONTH)==9||c.get(Calendar.MONTH)==11)
			{
				if(c.get(Calendar.DATE )==30)
				{
					c.add(Calendar.DATE,2);
				}
			}
			else
			{
				
			}//calendar不管哪個月最後一天都是31，判斷月份最後一天是否要再加一天結束
			tom=c.get(Calendar.MONTH)+"/"+c.get(Calendar.DATE);
	 		//System.out.println("明日為"+tom);
	 		c.add(Calendar.DATE,1);

			if((c.get(Calendar.MONTH)==2))//calendar不管哪個月最後一天都是31，判斷月份最後一天是否要再加一天開始
			{
				if((c.get(Calendar.YEAR)%400==0)||((c.get(Calendar.MONTH)==2)&&(c.get(Calendar.YEAR))%4==0&&(!(c.get(Calendar.YEAR)%100==0))))
				{
					if(c.get(Calendar.DATE )==30)
					{
						c.add(Calendar.DATE,2);
					}
				}
				else
				{
					
					if(c.get(Calendar.DATE )==29)
					{
						c.add(Calendar.DATE,3);
					}
				}
			}
			else if(c.get(Calendar.MONTH)==4||c.get(Calendar.MONTH)==6||c.get(Calendar.MONTH)==9||c.get(Calendar.MONTH)==11)
			{
				if(c.get(Calendar.DATE )==30)
				{
					c.add(Calendar.DATE,2);
				}
			}
			else
			{
				
			}//calendar不管哪個月最後一天都是31，判斷月份最後一天是否要再加一天結束
	 		dat=c.get(Calendar.MONTH)+"/"+c.get(Calendar.DATE);
	 		//System.out.println("後日為"+dat);
	 		String[] tomstr={"明天","明日","tomorrow","Tomorrow"};
	 		String[] datstr={"後天","後日","day after tomorrow"};
	 		
	 		int i,j;
	 		
	 		for(i=0;i<tomstr.length;i++)
	 		{
	 			if(strin.indexOf(tomstr[i])>0)
	 			{
	 				strin=strin.replace(tomstr[i],tom);
	 				//System.out.println("tom");
	 			}
	 			
	 		}
	 		
	 		for(i=0;i<datstr.length;i++)
	 		{
	 			if(strin.indexOf(datstr[i])>0)
	 			{
	 				strin=strin.replace(datstr[i],dat);
	 				//System.out.println("dat");
	 			}
	 			
	 		}
	 		
			return strin;
	 		
	 		
	 		
	 	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 	//public void onCreate() {
		 //super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
		int i,j,x,y;
		String matcher="";
		
		//String str = "a12/12bcaa12/20aad1:20bbb1:2xx/yyy:zz12月12iij:0ccss5號deefd543zxsds3點20或者3點dasds8.14asdjsa12.5dsipjjf1.1dsfsd";
		String str ="10號  下午2點在第三實驗室，進行第一次專題進度報告";
		String ori_str=str;
		//str="smartcalendarstart"+str+"smartcalendarend";//2011-09-27測試資料長度不足出錯，新方法2011-12-7新增start跟end
		//2011-09-08發現的bug為第二個日期開頭如果為個位數會出錯
		
		/*String key = ":";
		String key2 = "/";*/
		int keymatcherint=0;
		//String[] key2={"/",":","月","M","m","號","點",".","日","在","於"};
		String[] keymatcher1={"/","月","M","m","-","."};
		String[] keymatcher2={"日","號"};
		String[] keymatcher3={":"};
		String[] keymatcher4={"點","時"};
		String[] keymatcher5={"在","於","到","去","至"};
		//2011-10-11新增日這個關鍵字，目前還沒去針對這個字是否有BUG產生 日後須注意
		String[] keytimepm={"中午","下午","晚間","晚上","p.m","P.M"};//2011/10/12新增中午後時間判斷
		int keytime;//看keytimepm的數量而定
		String keytimepm2="";
		
			/*Time t=new Time(); 
	        t.setToNow(); 
	        int year = t.year;
	        int month = t.month+1;
	        int date = t.monthDay;*/
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		int date = Calendar.getInstance().get(Calendar.DATE);
	        String resyear="",resmonth="",resday="",reshour="",resmin="",resadd="";
	        String[] splittemp; 
		//String[] key2={"/"};
		
		
		
		String key="";
		String newstr;
		int integercheck;
		//System.out.println("原始輸入字串為:\t"+str);
		//System.out.println("\""+str+"\"");
		str=numtra(str);
		str=day(str);
		str=week(str);

		//System.out.println("\""+str+"\"");
   	char[] array = str.toCharArray();
		//System.out.println("輸入字串為:\t"+str);
		
		
		 //SimpleDateFormat  sdf = new SimpleDateFormat ("MM/dd");


		int datacount=0;
		String[] key2 = null;
		int matcherflag=0;
		
	for(keymatcherint=0;keymatcherint<5;keymatcherint++)
	{
		//System.out.println("keymatcherintstart="+keymatcherint);
		if(keymatcherint==0)
		{
			key2=keymatcher1;
			matcherflag=1;
			//System.out.println("matcherflag="+matcherflag);
		}
		else if(keymatcherint==1)
		{
			key2=keymatcher2;
			matcherflag=2;
			//System.out.println("matcherflag="+matcherflag);
		}
		else if(keymatcherint==2)
		{
			key2=keymatcher3;
			matcherflag=3;
			//System.out.println("matcherflag="+matcherflag);
		}
		else if(keymatcherint==3)
		{
			key2=keymatcher4;
			matcherflag=4;
			//System.out.println("matcherflag="+matcherflag);
		}
		else if(keymatcherint==4)
		{
			key2=keymatcher5;
			matcherflag=5;
			//System.out.println("matcherflag="+matcherflag);
		}
		//System.out.println("進入外層迴圈前");
   for(x=0;x<key2.length;x++)
   {
   	//System.out.println("進入外層迴圈後");
   	key=key2[x];
   	//System.out.println("測試字串為："+key+"結果為");
		List<Integer> matches = match(key, str);
		//System.out.println("進入內層迴圈前"+key);
		//for (Integer integer : matches) System.out.println("Match at: " + integer);
		for (Integer integer : matches)
		{
			//System.out.println("進入內層迴圈後");
			
			if(integer==0)
			{
				integercheck=integer;
				//System.out.println("進入內層迴圈後1");
			}
			else if(integer-1==0)
			{
				integercheck=integer-1;
				//System.out.println("進入內層迴圈後2");
			}
			else
			{
				integercheck=integer-2;
				//System.out.println("進入內層迴圈後3");
			}
			//System.out.println("進入內層迴圈後4");
			newstr="";
			for(i=integercheck;i<str.length();i++)
			{
				newstr+=array[i];
			}
			matcher+=format(dataint(newstr,key, newstr.length(),matcherflag),key,matcherflag);
			if(format(dataint(newstr,key, newstr.length(),matcherflag),key,matcherflag).contains("日期："))
			{
				//System.out.println("日期"+format(dataint(newstr,key, newstr.length()),key)+"+"+key);
				if(key==".")//2011-12-21針對"."做處理開始
				{
					String dotstr=format(dataint(newstr,key, newstr.length(),matcherflag),key,matcherflag);
					dotstr=dotstr.replace("日期：","");

					String[] dotarr=dotstr.split("\\.");
				 					
					resstring[0]+=dotarr[0]+"/"+dotarr[1]+",";
					resstring[1]+=dotarr[0]+":"+dotarr[1]+",";
					
				}//2011-12-21針對"."做處理結束
				else//2011-12-21針對"."做處理開始
				{
				resstring[0]+=allunified(format(dataint(newstr,key, newstr.length(),matcherflag),key,matcherflag),key,matcherflag)+",";
				}
			}
			if(format(dataint(newstr,key, newstr.length(),matcherflag),key,matcherflag).contains("時間："))
			{
				if(key!=".")//2011-12-21針對"."做處理開始
				{
				resstring[1]+=allunified(format(dataint(newstr,key, newstr.length(),matcherflag),key,matcherflag),key,matcherflag)+",";
				}//2011-12-21針對"."做處理結束
			}
			if(format(dataint(newstr,key, newstr.length(),matcherflag),key,matcherflag).contains("地點："))
			{
				resstring[2]+=allunified(format(dataint(newstr,key, newstr.length(),matcherflag),key,matcherflag),key,matcherflag)+",";
			}
			
			matcher+="\t";
			if((matcherflag==3)||(matcherflag==4)||key==".")//2011/10-12新增下午過後的時間判斷
			{
				
				//System.out.println("inpm"+str);
				for(keytime=0;keytime<keytimepm.length;keytime++)
				{
					
					int timeflag=0;
					keytimepm2=keytimepm[keytime];
					List<Integer> matchestime = match(keytimepm2, str);
					for (Integer integertime : matchestime)
			 		{
						//System.out.println("time"+integertime);
						timeflag=1;
			 		}
				
					if(timeflag==1&&((dataint(newstr,key, newstr.length(),matcherflag))!=""))//2011-12-7因親愛的學弟~11/32那天晚上有6點半有空嗎?要不要去餐廳吃飯~~揪咪~^.<避免"^.<"轉成數字出錯額外新增(dataint(newstr,key, newstr.length()))!=""判斷
					{
						
						matcher+=timepm((dataint(newstr,key, newstr.length(),matcherflag)),key, (dataint(newstr,key, newstr.length(),matcherflag)).length());
						resstring[1]+=allunified(timepm((dataint(newstr,key, newstr.length(),matcherflag)),key, (dataint(newstr,key, newstr.length(),matcherflag)).length()),key,matcherflag);
						break;
					}

					
				}
	 			
			}
			
			matcher+="\t";
			

			resstring[0]=resstring[0].replace("日期：","");
			resstring[0]=resstring[0].replace("時間：","");
			resstring[1]=resstring[1].replace("日期：","");
			resstring[1]=resstring[1].replace("時間：","");
			resstring[2]=resstring[2].replace("地點：","");
			matcher="";

			matcher+="String0="+resstring[0]+"\t String1="+resstring[1]+"\t string2="+resstring[2];
			
			/*File SDFile = android.os.Environment
			.getExternalStorageDirectory();
	    	
			File myFile = new File(SDFile.getAbsolutePath()
					+ File.separator + "/smartcalendar/myfile");*/
			//System.out.println("test");
			
			//String strin="12/27 14:45 在I5401教室，資工系第一次專題進度報告";
			String strin=str;
			String readsdstr="";
			
			String readstr="";
			String[] readstrsplit;
			
		    /*if (Environment.getExternalStorageState().equals(
	                Environment.MEDIA_MOUNTED)) {
	            try {
	                FileInputStream inputStream = new FileInputStream(myFile);
	                byte[] read = new byte[inputStream.available()];
	                inputStream.read(read);
	                readstr=new String(read);
	                //System.out.println(readstr);
	                
	            } catch (Exception e) {
	               
	            }
		    }*/
		    readstrsplit=readstr.split(",");
		    int readi;
		  

		    
		    if(readstrsplit.length>0)
		    {
		    	  for(readi=0;readi<readstrsplit.length;readi++)
		  	    {
		  	    	
		  	    	//System.out.println(readstrsplit[readi]);
		  	    	
		  	    	if(strin.indexOf(readstrsplit[readi])>0)
		  	    	{
		  	    		if(readsdstr.equals(","))
		  	    		{readsdstr=readstrsplit[readi]+",";}
		  	    		else
		  	    		{readsdstr+=readstrsplit[readi]+",";}	
		  	    	}
		  	    }
		    	
		    	
		    }
		    //System.out.println(readsdstr);
		   resstring[2]+=readsdstr;



			//System.out.println("進入內層迴圈後5");
			
			//str=str.replace("smartcalendar","");
			//matcher+="\n"+str+"\n";
			/*try {
	            //取得SD卡路徑
	               File SDCardpath = Environment.getExternalStorageDirectory();
	               //File myDataPath = new File( SDCardpath.getAbsolutePath() + "/myData" );
	               //if( !myDataPath.exists() ) myDataPath.mkdirs();
	               //將資料寫入到SD卡
	               //FileWriter myFile = new FileWriter("/sdcard/myData/test.txt" );
	               FileWriter myFile = new FileWriter( SDCardpath.getAbsolutePath() + "/myData/test.txt.txt" );
	               myFile.write(matcher);
	               myFile.close();
	               
	  } catch (IOException e) {
	   e.printStackTrace();
	  }*/
	 		
		//System.out.println("結束1");
			//matcher=dataint(newstr,key, newstr.length());
	         //Date data= sdf.parse(matcher);
			//System.out.print(data+"\n");
			
		}
			
		
		//System.out.println("keymatcherintend11="+keymatcherint);
   }
   //System.out.println("結束5");
	}
	
		String[] datearr=resstring[0].split(",");
			String[] timearr=resstring[1].split(",");
			
			
			
			int datewhile,timewhile;
			Date[] resdata=new Date[datearr.length];
			Date[] restime=new Date[timearr.length];
			for(datewhile=0;datewhile<datearr.length;datewhile++)
			{
				try {
				//System.out.println("aaa1");
	 			//System.out.println(datearr[datewhile]);
				resdata[datewhile]=datetodate(datearr[datewhile]);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			for(timewhile=0;timewhile<timearr.length;timewhile++)
			{
				
					
				try {
				//System.out.println("aaa2");
	 			//System.out.println(timearr[timewhile]);
	 			restime[timewhile]=datetodate(timearr[timewhile]);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			
			}
			
			

	        resadd=resstring[2];
	        
	       
	       
	        	for(datewhile=0;datewhile<datearr.length;datewhile++)
	        	{
	        		if(datearr[datewhile]!="")
	        		{
	        			int yeartmp=0;
	        		splittemp=datearr[datewhile].split("/");
	        		//System.out.println("datelength="+datearr[0]);
	        		//System.out.println("resyear="+year+"+"+splittemp[0]+"+"+splittemp[1]);
	        		if(Integer.valueOf(splittemp[0])<month)
	        		{
	        			yeartmp=1;
	        		}
	        		resyear+=(year+yeartmp)+",";
	        		//System.out.println("現在年份為"+resyear);
	        		if(!(resstring[3].indexOf(resyear)>0))
	        		{resstring[3]+=resyear+",";}
	        		resmonth+=splittemp[0]+",";
	        		resday+=splittemp[1]+",";
	        		}
	        		
	        	}
	      /*String[] testarr = {""};
	        	//System.out.println("timelength="+testarr.length);*/
	        	//System.out.println("timelength"+timearr.length);
	        for(timewhile=0;timewhile<timearr.length;timewhile++)
	        {
	        	//System.out.println("timewhile<timearr.length");
	        	if(timearr[timewhile]!="")
	        	{
	        		//System.out.println("timewhile"+timearr[timewhile]);
	        	splittemp=timearr[timewhile].split("\\:");
	        	reshour+=splittemp[0]+",";
	        	resmin+=splittemp[1]+",";
	        	}
	        	

	        }
	        
	        
	        //System.out.println("split");
	        
	        if(resyear=="")
	        {
	        	resyear+=",";
	        }
	        if(resmonth=="")
	        {
	        	resmonth+=",";
	        }
	        if(resday=="")
	        {
	        	resday+=",";
	        }
	        if(reshour=="")
	        {
	        	reshour+=",";
	        }
	        if(resmin=="")
	        {
	        	resmin+=",";
	        }
	        if(resadd=="")
	        {
	        	resadd+=",";
	        }
	        /*
	        resultyear=resyear.split(",");
	        resultmonth=resmonth.split(",");
	        resultday=resday.split(",");
	        
	        resulthour=reshour.split(",");
	        resultmin=resmin.split(",");
	        
	        resultadd=resadd.split(",");
	        
	        int resulttemp;
	        for(resulttemp=0;resulttemp<resultyear.length;resulttemp++)
	        {
	        	System.out.println(resultyear[resulttemp]);
	        }
	        for(resulttemp=0;resulttemp<resultmonth.length;resulttemp++)
	        {
	        	System.out.println(resultmonth[resulttemp]);
	        }
	        for(resulttemp=0;resulttemp<resultday.length;resulttemp++)
	        {
	        	System.out.println(resultday[resulttemp]);
	        }
	        
	        for(resulttemp=0;resulttemp<resulthour.length;resulttemp++)
	        {
	        	System.out.println(resulthour[resulttemp]);
	        }
	        for(resulttemp=0;resulttemp<resultmin.length;resulttemp++)
	        {
	        	System.out.println(resultmin[resulttemp]);
	        }
	        
	        for(resulttemp=0;resulttemp<resultadd.length;resulttemp++)
	        {
	        	System.out.println(resultadd[resulttemp]);
	        }
	        */
	        
	        
	        
	        
	        
	        
		
		
		//System.out.println("結束3");
   	str=str.replace("smartcalendarend","");
   	
   	str=str.replace("smartcalendarstart","");
   	
   		/*System.out.println("resyear="+resyear);
			System.out.println("resmonth="+resmonth);
			System.out.println("resday="+resday);
			System.out.println("reshour="+reshour);
			System.out.println("resmin="+resmin);
			System.out.println("resadd="+resadd);*/
			
			//String[] resultyear = new String[timearr.length];
			//resultyear[0]="ccc";
			//System.out.println(resultyear[0]);
   			System.out.println("\n"+ori_str);
	    	System.out.println("resstring[0]="+resstring[0]);
	    	System.out.println("resstring[1]="+resstring[1]);
	    	System.out.println("resstring[2]="+resstring[2]);
		 //this.finish();
		//System.out.println("結束4");
		//判斷位置值 
		//System.out.println((matches.equals(Arrays.asList(4, 10)) ? "OK" : "Failed"));
		
	}

}
