package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;

import test.vo.BoardVo;


public class BoardDao {
	public int getCount(String field,String keyword) {//전체글의 갯수 구하기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select NVL(count(num),0) from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int num=rs.getInt(1);
				return num;	
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);		
		}
	}
	
	public int getMaxNum() {//가장 큰 글번호 얻어오기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql=" select NVL(max(num),0) as maxnum from board ";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int num=rs.getInt("maxnum");
				return num;	
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);		
		}
	}

		public int insert(BoardVo vo) {
			Connection con=null;
			PreparedStatement pstmt=null;
			PreparedStatement pstmt1=null;
			try {
				con=JdbcUtil.getConn();
				int boardNum=getMaxNum() + 1;//등록될 글번호
				String sql="insert into board values(?,?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,boardNum);
				pstmt.setString(2,vo.getWriter());
				pstmt.setString(3,vo.getTitle());
				pstmt.setString(4,vo.getContent());
				pstmt.setInt(5,vo.getHit());
				pstmt.setInt(6,vo.getPwd());
			
				return pstmt.executeUpdate();
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			}finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con, pstmt1, null);
			}		
		}
		//글목록
		public ArrayList<BoardVo> list(int startRow,int endRow){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=JdbcUtil.getConn();
				String sql="select * from " + 
						"(" + 
						"	select aa.*,rownum rnum from" + 
						"	(" + 
						"		select * from board" + 
						"	)aa" + 
						") where rnum>=? and rnum<=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,startRow);
				pstmt.setInt(2,endRow);
				rs=pstmt.executeQuery();
				ArrayList<BoardVo> list=new ArrayList<BoardVo>();
				while(rs.next()) {
					int num=rs.getInt("num");
					String writer=rs.getString("writer");
					String title=rs.getString("title");
					String content=rs.getString("content");
					BoardVo vo=new BoardVo(num, writer, title, content, 0, 0);
					list.add(vo);
				}
				return list;
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}finally {
				JdbcUtil.close(con, pstmt, rs);
			}
		}
		
		public BoardVo detail(int num){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=JdbcUtil.getConn();
				String sql="select * from board where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,num);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					String writer=rs.getString("writer");
					String title=rs.getString("title");
					String content=rs.getString("content");
					int hit=rs.getInt("hit");
					BoardVo vo=new BoardVo(num, writer, title, content, hit, 0);
					return vo;
				}
				return null;
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}finally {
				JdbcUtil.close(con, pstmt, rs);
			}
		}

		
		public int delete(String num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=JdbcUtil.getConn();
				String sql="delete from board where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,num);
				return pstmt.executeUpdate();
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				}catch(SQLException se) {
					System.out.println(se.getMessage());
				}
			}
		}
		
		
		public ArrayList<BoardVo> list(int startRow,int endRow,
				String field,String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="";
		
			if(field==null || field.equals("")) {
			sql="select * from " + 
					"(" + 
					"	select aa.*,rownum rnum from" + 
					"	("+ 
					"		select * from board " + 
					"		order by num desc" + 
					"	)aa" + 
					") where rnum>=? and rnum<=?";
			}else {//검색조건이 있는 경우
			sql="select * from " + 
					"(" + 
					"	select aa.*,rownum rnum from" + 
					"	("+ 
					"		select * from board where "+ field+ " like '%"+ keyword+ "%'" + 
					"		order by num desc" + 
					"	)aa" + 
					") where rnum>=? and rnum<=?";
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			rs=pstmt.executeQuery();
			ArrayList<BoardVo> list=new ArrayList<BoardVo>();
			while(rs.next()) {
				BoardVo vo=new BoardVo(rs.getInt("num"),
						rs.getString("writer"),
						rs.getString("title"), 
						rs.getString("content"),
						rs.getInt("hit"),
						0);
				
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}	
	}
		

		
}



