T = [+1 +1; ... 
     -1 +1; ...
     -1 -1];
axis([-1 1 -1 1 -1 1])
set(gca,'box','on'); 
axis manual; 
hold on; 
plot3(T(1,:),T(2,:),T(3,:),'r*')
title('Hopfield Network State Space') 
xlabel('a(1)');
ylabel('a(2)');
zlabel('a(3)');
view([37.5 30]);
% newhop Create a Hopfield recurrent network. 
net = newhop(T);
% rands is a weight/bias initialization function.
% rands(S,R) returns an S-by-R matrix of random values. 
a = {rands(3,1)};
[y,Pf,Af] = sim(net,{1 10},{},a);
record = [cell2mat(a) cell2mat(y)];
start = cell2mat(a);
hold on
plot3(start(1,1),start(2,1),start(3,1),'bx', ... 
      record(1,:),record(2,:),record(3,:))
color = 'rgbmy';
for i=1:25
    a = {rands(3,1)};
    [y,Pf,Af] = sim(net,{1 10},{},a); 
    record=[cell2mat(a) cell2mat(y)];
    start=cell2mat(a); 
    plot3(start(1,1),  start(2,1),  start(3,1),  'k*', ... 
          record(1,:), record(2,:), record(3,:), color(rem(i,5)+1))
end

P = [ 1.0 -1.0 -0.5 1.00 1.00 0.0; ...
      0.0 0.0 0.0 0.00 0.00 -0.0; ... 
     -1.0 1.0 0.5 -1.01 -1.00 0.0];
cla 
plot3(T(1,:),T(2,:),T(3,:),'r^') 
color = 'rgbmy';
for i=1:6
a = {P(:,i)};
[y,Pf,Af] = sim(net,{1 10},{},a); 
record=[cell2mat(a) cell2mat(y)];
start=cell2mat(a); 
plot3(start(1,1),start(2,1),start(3,1),'k*', ... 
      record(1,:),record(2,:),record(3,:),color(rem(i,5)+1))
end
