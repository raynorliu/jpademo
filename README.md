# Jpa 常用功能演示

## 项目简介
没有继承框架的基础调用展示
调用方法都在test目录下



### 特点
### JPA中实体的四种状态
> - 1.new 新建状态
> - 2.managed 托管状态
		1）在事务关闭前查找到的对象属于托管状态
		2）此时对对象进行修改，会将修改后的数据存入jdbc的批处理中，修改后的值会在事务提交时同步到数据库中，不需要求调用其他方法对对象进行操作
> - 3.游离(脱管)状态
		对游离状态的实体更新必须使用实体管理器持久化API
> - 4.删除状态

### JPA支持的2种传参方式：
> - 1.使用占位符方式----> ?1 <br>
> - 2.使用位参方式----> :name <br>
<pre>
	1.使用占位符方式----> ?1 <br>
	2.使用位参方式----> :name <br>
</pre>


## 一些方法说明
- 创建实体管理工厂,使用完毕后记得关闭工厂

<code> EntityManagerFactory factory = Persistence.createEntityManagerFactory(String persistenceUnitName)</code>


- EntityManager entityManager = factory.createEntityManager()--->通过工厂获取实体管理器
	使用完毕后记得关闭管理器


- EntityManager.getReference(entityClass, primaryKey)
	1.属于延迟加载，返回的是代理对象，只有真正访问里面的属性时才开始加载数据
	2.注意：不要在session关闭后访问，session关闭后代理对象不存在了
	
- EntityManager.save(entity)---> 保存新建

- EntityManager.merge(entity)---> 存储游离(脱管)状态的对象

- EntityManager.remove(entity)---> 删除对象

- EntityManager.remove(entity)---> 删除对象

- Query query = entityManager.createQuery("SELECT p FROM Person p")
	List<Person> persons = query.getResultList()---> 返回列表
	
- Query query = entityManager.createQuery("SELECT count(*) FROM Person");
	Long count = (Long) query.getSingleResult()---> 返回统计结果

- Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.id = ?1");
		query.setParameter(1,1L);
		Person person = (Person) query.getSingleResult()---> 返回单个查询结果

#### 注意事项

- 对数据进行修改操作必须开启事务，不然修改后的数据无法同步到数据库中

- 删除数据时只能对托管的对象进行删除	

## 联系方式（Contact information）

- Email: <l156572553@gmail.com>
- QQ: 156572553
- Wechat: Aognob

## 结束语

- 真心感谢志同道合的人, 这个真的很重要, 也希望你能一起参与!
