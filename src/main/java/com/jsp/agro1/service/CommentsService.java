package com.jsp.agro1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro1.dao.CommentsDao;
import com.jsp.agro1.dao.PostDao;
import com.jsp.agro1.dao.UserDao;
import com.jsp.agro1.entity.Comments;
import com.jsp.agro1.entity.Post;
import com.jsp.agro1.entity.User;
import com.jsp.agro1.exception.CommentNotFound;
import com.jsp.agro1.exception.PostNotFound;
import com.jsp.agro1.exception.UserNotFound;
import com.jsp.agro1.util.ResponseStructure;

@Service
public class CommentsService {
	@Autowired
	private CommentsDao cdao;
	@Autowired
	private PostDao pdao;
	@Autowired
	private UserDao udao;
	
	public ResponseEntity<ResponseStructure<Comments>> doComment(int pid,int uid,String comment){
		Post pdb = pdao.fetchPostByID(pid);
		if(pdb!=null) {
			User udb = udao.fetchByID(uid);
			if(udb!=null) {
				Comments comm = new Comments();
				comm.setComment(comment);
				comm.setUser(udb);
				Comments cdb = cdao.saveComments(comm);
				List<Comments> list = pdb.getComments();
				list.add(cdb);
				pdb.setComments(list);
				pdao.updatePost(pdb);
				ResponseStructure<Comments> s = new ResponseStructure<Comments>();
				s.setData(cdb);
				s.setMsg("commented success");
				s.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<Comments>>(s,HttpStatus.ACCEPTED);
			}
			else {
				throw new UserNotFound("user not found for your search:"+uid);
			}
		}
		else {
			throw new PostNotFound("post not found for your search:"+pid);
		}
	}
	public ResponseEntity<ResponseStructure<Comments>> deleteComment(int id){
		Comments comm = cdao.fetchByID(id);
		if(comm!=null) {
			List<Post> li = pdao.fetchAll();
			for (Post post : li) {
				List<Comments> c = post.getComments();
				if(c.contains(comm)) {
					c.remove(comm);
					pdao.updatePost(post);
					cdao.deleteCommentsByID(id);
					break;
				}
			}
			ResponseStructure<Comments> s = new ResponseStructure<Comments>();
			s.setData(comm);
			s.setMsg("comment deleted");
			s.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Comments>>(s,HttpStatus.OK);
		}
		else {
			throw new CommentNotFound("comment not found for your search:"+id);
		}
	}
}
