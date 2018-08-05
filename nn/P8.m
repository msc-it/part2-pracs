% 1. Set two arbitrary equilibrium points
T = [+1 -1; ...
      -1 +1];
% ... and plot them using red stars
plot(T(1,:),T(2,:),'r*')
title('Hopfield Network State Space')
xlabel('a(1)');
ylabel('a(2)');

% 2. Generate a hopfield network using the training points T
net = newhop(T);

% 3. 
hold on;
for i=1:25
    a = {rands(2,1)}; % generate a random 2x1 cell array
    y = net({20},{},a);

    start = cell2mat(a); % the random point
    record = [start cell2mat(y)]; % generated solution from the nearest equilibrium point 
                                  % to the random point

    plot(start(1,1),start(2,1), 'kx'); %plot the point as x
    plot(record(1,:),record(2,:)); % plot the solution line
end
hold off