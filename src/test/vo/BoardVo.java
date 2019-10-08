package test.vo;

public class BoardVo {
	private int num;//글번호
	private String  writer;//작성자
	private String   title;//제목
	private String   content; //내용
	private int     hit;//조회수
	private int    pwd;// 비밀번호(삭제시 필요)

	
	public BoardVo (){}


	public BoardVo(int num, String writer, String title, String content, int hit, int pwd) {
		super();
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.pwd = pwd;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}


	public int getPwd() {
		return pwd;
	}


	public void setPwd(int pwd) {
		this.pwd = pwd;
	}

	
	
	
	
}