package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@ToString
public class Criteria {	// �˻��� ����

  private int pageNum;	// ������ ��ȣ
  private int amount;	// �� �������� �� ���� �����͸� ������ ������
//  private int startNum;	// db���� limit�� ���۰�
  
//  private String type;
//  private String keyword;

  	public Criteria() {		// 1���) �⺻ �ʱⰪ�� �ִ���
  		this(1, 10);		// �⺻�� 1������, 10��
  	}

  	public Criteria(int pageNum, int amount) {	// 2���) �ʱⰪ�� �ִ���
  		this.pageNum = pageNum;
  		this.amount = amount;
  	}

	public int getPageNum() {
		return pageNum;
	}
	
  	// BoardMapper.xml���� ������ �� �ڵ����� �����ؼ� ��������
	public int getStartNum() {
		return (pageNum-1)*amount;	// db���� limit�� ���۰� ���
		// ex> 10���� ������ �� 3�������� ���� �ʹٸ� limit 20,10
	}
	
	public int getAmount() {
		return amount;
	}
  
  
  
//  public String[] getTypeArr() {
//    
//    return type == null? new String[] {}: type.split("");
//  }
}
