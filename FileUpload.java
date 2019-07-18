package fileio;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class FileUpload {
	List<Customer> list=new ArrayList<>();
	static String filePath;
	 
	public void readFile()
	{
		FileReader reader;
		
		try {
			reader = new FileReader(filePath);
			BufferedReader bufferReader=new BufferedReader(reader);
			String line=bufferReader.readLine();
			while(line!=null)
			{
				/*System.out.println(line);*/
				String arr[]=line.split("~",16);
				Customer c1=new Customer();
		        list.add(c1);
		        if(arr[0].isEmpty())
		        	c1.setCustomerCode(null);
		        else
				c1.setCustomerCode(arr[0]);
		        if(arr[1].isEmpty())
		        	c1.setCustomerName(null);
		        else
				c1.setCustomerName(arr[1]);
		        if(arr[2].isEmpty())
		        	c1.setCustomerAddress1(null);
		        else
				c1.setCustomerAddress1(arr[2]);
		        if(arr[3].isEmpty())
		        	c1.setCustomerAddress2(null);
		        else
				c1.setCustomerAddress2(arr[3]);
		        if(arr[4].isEmpty())
		        	c1.setCustomerPinCode(0);
		        else
				c1.setCustomerPinCode(Integer.parseInt(arr[4]));
		        if(arr[5].isEmpty())
		        	c1.setEmail(null);
		        else
				c1.setEmail(arr[5]);
		        if(arr[6].isEmpty())
		        	c1.setContactNo(0);
		        else
				c1.setContactNo(Long.parseLong(arr[6]));
		        if(arr[7].isEmpty())
		        	c1.setPrimaryContactPerson(null);
		        else
				c1.setPrimaryContactPerson(arr[7]);
		        if(arr[8].isEmpty())
		        	c1.setRecordStatus(null);
		        else
				c1.setRecordStatus(arr[8]);
		        if(arr[9].isEmpty())
		        	c1.setFlag(arr[9]);
		        else
				c1.setFlag(arr[9]);
				try{
					if(arr[10].isEmpty())
						c1.setCreateDate(null);
					else
				c1.setCreateDate(new SimpleDateFormat("dd/MMM/yyyy").parse(arr[10]));
				}
				catch(ParseException e)
				{
					e.printStackTrace();
				}
				if(arr[11].isEmpty())
					c1.setCreatedBy(null);
				else
				c1.setCreatedBy(arr[11]);
				
				try
				{
					if(arr[12].isEmpty())
						c1.setModifiedDate(null);
					else
				c1.setModifiedDate(new SimpleDateFormat("dd/MMM/yyyy").parse(arr[12]));
				}
				catch(ParseException e)
				{
					e.printStackTrace();
				}
				if(arr[13].isEmpty())
					c1.setModifiedBy(null);
				else	
				c1.setModifiedBy(arr[13]);
				try
				{
					if(arr[14].isEmpty())
						c1.setAuthorizedDate(null);
					else
				c1.setAuthorizedDate(new SimpleDateFormat("dd/MMM/yyyy").parse(arr[14]));
				}
				catch(ParseException e)
				{
					e.printStackTrace();
				}
				if(arr[15].isEmpty())
					c1.setAuthorizedBy(null);
				else
				c1.setAuthorizedBy(arr[15]);
				line=bufferReader.readLine();	
				
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void display()
	{
	  for(int i=0;i<list.size();i++)
		{
			//System.out.println(list.get(i));
		System.out.println(list.get(i).getCustomerCode()+"\t"+list.get(i).getCustomerName()+"\t"+list.get(i).getCustomerAddress1()+"\t"+list.get(i).getCustomerAddress2()+"\t"+list.get(i).getCustomerPinCode()+"\t"+list.get(i).getEmail()+"\t"+list.get(i).getContactNo()+"\t"+list.get(i).getPrimaryContactPerson()+"\t"+list.get(i).getRecordStatus()+"\t"+list.get(i).getFlag()+"\t"+list.get(i).getCreatedBy()+"\t"+list.get(i).getCreateDate()+"\t"+list.get(i).getModifiedBy()+"\t"+list.get(i).getModifiedDate()+"\t"+list.get(i).getAuthorizedBy()+"\t"+list.get(i).getAuthorizedDate());
		}
	}
	
	public void writeDb()
	{
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			System.out.println("connected");
			//con=DriverManager.getConnection("jdbc:oracle:thin:@10.1.50.198:1521:orcl","sh","sh");
			for(int i=0;i<list.size();i++)
			{
			ps=con.prepareStatement("insert into customer0101(customer_code,customer_name,cust_address1,cust_address2,pin,email,contactno,contact_person,record_status,flag,create_date,created_by,modified_date,modified_by,authorized_date,authorized_by) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1,list.get(i).getCustomerCode());
			ps.setString(2,list.get(i).getCustomerName());
			ps.setString(3,list.get(i).getCustomerAddress1());
			ps.setString(4,list.get(i).getCustomerAddress2());
			ps.setInt(5,list.get(i).getCustomerPinCode());
			ps.setString(6,list.get(i).getEmail());
			ps.setLong(7,list.get(i).getContactNo());
			ps.setString(8,list.get(i).getPrimaryContactPerson());
			ps.setString(9,list.get(i).getRecordStatus());
			ps.setString(10,list.get(i).getFlag());
			
			if(list.get(i).getCreateDate()!=null)
			ps.setDate(11,new java.sql.Date(list.get(i).getCreateDate().getTime()));
			else
			ps.setDate(11,(Date)list.get(i).getCreateDate());
			ps.setString(12,list.get(i).getCreatedBy());
			
			
			
			if(list.get(i).getModifiedDate()!=null)
			ps.setDate(13,new java.sql.Date(list.get(i).getModifiedDate().getTime()));
			else
			ps.setDate(13,(Date)list.get(i).getModifiedDate());
			ps.setString(14,list.get(i).getModifiedBy());
			
			
			ps.setString(15,list.get(i).getAuthorizedBy());
			
		    if(list.get(i).getAuthorizedDate()!=null)
			ps.setDate(15,new java.sql.Date(list.get(i).getAuthorizedDate().getTime()));
			else
			ps.setDate(15,(Date)list.get(i).getAuthorizedDate());	
		    ps.setString(16,list.get(i).getAuthorizedBy());
			
		    
			ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		filePath=scanner.next();
		FileUpload f=new FileUpload();
		f.readFile();
		f.display();
		f.writeDb();
		

	}

}
//D:\\testing\\File1.txt