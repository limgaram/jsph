package board.article;

import java.sql.ResultSet;
import java.sql.SQLException;

import board.RowMapper;

public class LikeRowMapper implements RowMapper<Like> {

	@Override
	public Like getRow(ResultSet rs) throws SQLException {

		int aid = rs.getInt("aid");
		int mid = rs.getInt("mid");
		String regDate = rs.getString("regDate");

		Like like = new Like(aid, mid, regDate);
		
		return like;
	}

}
